package com.yt8492.router

import com.yt8492.UUIDHelper
import com.yt8492.infra.repository.ContentRepository
import com.yt8492.infra.repository.DeskRepository
import com.yt8492.infra.repository.ItemInfoRepository
import com.yt8492.model.ItemInfo
import com.yt8492.router.json.DeskIdJson
import com.yt8492.router.json.ItemInfoJson
import com.yt8492.router.json.ItemInfoListJson
import com.yt8492.service.S3Service
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.streamProvider
import io.ktor.locations.*
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.put
import io.ktor.util.KtorExperimentalAPI
import java.io.File

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
fun Route.deskRoute() {
    put("/desk") {
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
                val itemId = UUIDHelper.createUUID()
                ContentRepository.save(itemId, value)
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    null, // TODO: create QR when text is URL
                    name
                ).let(::model2Json)
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
        }
        part.dispose()
    }

    put<DeskRequest> { param ->
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
                if (ItemInfoRepository.isExist(param.deskId, name)) {
                    ItemInfoRepository.deleteByDeskIdAndName(param.deskId, name)
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
                val itemId = UUIDHelper.createUUID()
                ContentRepository.save(itemId, value)
                val itemInfo = ItemInfoRepository.save(
                    itemId,
                    param.deskId,
                    name,
                    type,
                    null, // TODO: create QR when text is URL
                    name
                ).let(::model2Json)
                call.respond(
                    HttpStatusCode.OK,
                    itemInfo
                )
            }
        }
        part.dispose()
    }

    get<DeskRequest> { param ->
        val itemInfoList = ItemInfoRepository.findAllByDeskId(param.deskId)
            .map(::model2Json)
        call.respond(
            HttpStatusCode.OK,
            ItemInfoListJson(itemInfoList)
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