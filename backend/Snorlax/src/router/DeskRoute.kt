package com.yt8492.router

import com.yt8492.UUIDHelper
import com.yt8492.infra.repository.ContentRepository
import com.yt8492.infra.repository.DeskRepository
import com.yt8492.infra.repository.ItemInfoRepository
import com.yt8492.model.ItemInfo
import com.yt8492.router.json.DeskIdJson
import com.yt8492.router.json.ItemInfoJson
import com.yt8492.router.json.ItemInfoListJson
import com.yt8492.service.QRFactory
import com.yt8492.service.S3Service
import io.ktor.application.call
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.locations.*
import io.ktor.request.receiveMultipart
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
fun Route.deskRoute() {
    post("/desk") {
        call.response.header(HttpHeaders.AccessControlAllowOrigin, "*")
        call.response.header(HttpHeaders.AccessControlAllowMethods, "${HttpMethod.Get.value}, ${HttpMethod.Post.value}, ${HttpMethod.Put.value}")
        val deskId = UUIDHelper.createUUID()
        DeskRepository.save(deskId)
        call.respond(
            HttpStatusCode.OK,
            DeskIdJson(deskId)
        )
    }

    @Location("/desk/{deskId}")
    data class DeskRequest(val deskId: String)
    post<DeskRequest> { param ->
        call.response.header(HttpHeaders.AccessControlAllowOrigin, "*")
        call.response.header(HttpHeaders.AccessControlAllowMethods, "${HttpMethod.Get.value}, ${HttpMethod.Post.value}, ${HttpMethod.Put.value}")
        val multipart = call.receiveMultipart()
        val part = multipart.readPart() ?: run {
            call.respond(HttpStatusCode.BadRequest, "multipart is null")
            return@post
        }
        if (DeskRepository.isNotExist(param.deskId)) {
            call.respond(HttpStatusCode.BadRequest, "deskId ${param.deskId} not found")
            return@post
        }
        when (part) {
            is PartData.FileItem -> {
                val name = part.originalFileName ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "filename must not be null"
                    )
                    return@post
                }
                val type = "${part.contentType?.contentType}/${part.contentType?.contentSubtype}"
                if (ItemInfoRepository.isExist(param.deskId, name)) {
                    call.respond(
                        HttpStatusCode.Conflict,
                        "same filename already exist"
                    )
                    return@post
                }
                val file = File(name).apply {
                    part.streamProvider().buffered().use { input ->
                        outputStream().buffered().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
                val itemId = S3Service.postFile(param.deskId, file)
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    null,
                    null // TODO: create Thumbnail when file is video
                ).let(::model2Json)
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
            is PartData.FormItem -> {
                val name = part.name ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "name must not be null"
                    )
                    return@post
                }
                val value = part.value
                val type = if ("""http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?""".toRegex().matches(value)) {
                    "url"
                } else {
                    "text"
                }
                val qrId = async {
                    if (type == "url") {
                        val qr = QRFactory.createQR(value)
                        val qrId = S3Service.postFile(param.deskId, qr)
                        ItemInfoRepository.save(qrId, param.deskId, qrId, "image", null, null)
                        qrId
                    } else {
                        null
                    }
                }
                val itemId = UUIDHelper.createUUID()
                val job = launch {
                    ContentRepository.save(itemId, value)
                }
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    qrId.await(),
                    null
                ).let(::model2Json)
                job.join()
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
        }
        part.dispose()
    }

    put<DeskRequest> { param ->
        call.response.header(HttpHeaders.AccessControlAllowOrigin, "*")
        call.response.header(HttpHeaders.AccessControlAllowMethods, "${HttpMethod.Get.value}, ${HttpMethod.Post.value}, ${HttpMethod.Put.value}")
        val multipart = call.receiveMultipart()
        val part = multipart.readPart() ?: run {
            call.respond(HttpStatusCode.BadRequest, "multipart is null")
            return@put
        }
        when (part) {
            is PartData.FileItem -> {
                val name = part.originalFileName ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "filename must not be null"
                    )
                    return@put
                }
                val type = "${part.contentType?.contentType}/${part.contentType?.contentSubtype}"
                launch {
                    if (ItemInfoRepository.isExist(param.deskId, name)) {
                        ItemInfoRepository.deleteByDeskIdAndName(param.deskId, name)
                    }
                }
                val file = File(name).apply {
                    part.streamProvider().buffered().use { input ->
                        outputStream().buffered().use { output ->
                            input.copyTo(output)
                        }
                    }
                }
                val itemId = S3Service.postFile(param.deskId, file)
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    null,
                    null // TODO: create Thumbnail when file is video
                ).let(::model2Json)
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
            is PartData.FormItem -> {
                val name = part.name ?: run {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "name must not be null"
                    )
                    return@put
                }
                val value = part.value
                val type = if ("""http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?""".toRegex().matches(value)) {
                    "url"
                } else {
                    "text"
                }
                val qrId = async {
                    if (type == "url") {
                        val qr = QRFactory.createQR(value)
                        val qrId = S3Service.postFile(param.deskId, qr)
                        ItemInfoRepository.save(qrId, param.deskId, qrId, "image", null, null)
                        qrId
                    } else {
                        null
                    }
                }
                val itemId = UUIDHelper.createUUID()
                val job = launch {
                    ContentRepository.save(itemId, value)
                }
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    qrId.await(),
                    null
                ).let(::model2Json)
                job.join()
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
        }
        part.dispose()
    }

    get<DeskRequest> { param ->
        call.response.header(HttpHeaders.AccessControlAllowOrigin, "*")
        call.response.header(HttpHeaders.AccessControlAllowMethods, "${HttpMethod.Get.value}, ${HttpMethod.Post.value}, ${HttpMethod.Put.value}")
        val itemInfoList = ItemInfoRepository.findAllByDeskId(param.deskId)
            .map(::model2Json)
        val qrIdList = itemInfoList.mapNotNull {
            it.qr_id
        }
        val itemInfoListWithoutQR = itemInfoList.filterNot {
            qrIdList.contains(it.item_id)
        }
        call.respond(
            HttpStatusCode.OK,
            ItemInfoListJson(itemInfoListWithoutQR)
        )
    }
}

private fun model2Json(itemInfo: ItemInfo): ItemInfoJson = ItemInfoJson(
    itemInfo.itemId,
    itemInfo.name,
    itemInfo.type.value,
    itemInfo.qrId,
    itemInfo.thumbnailId
)