package com.yt8492.router

import com.yt8492.infra.repository.ContentRepository
import com.yt8492.infra.repository.DeskRepository
import com.yt8492.infra.repository.ItemInfoRepository
import com.yt8492.model.ContentType
import com.yt8492.service.S3Service
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
fun Route.itemRoute() {
    @Location("/desk/{deskId}/{itemId}")
    data class ItemRequest(val deskId: String, val itemId: String)
    get<ItemRequest> { param ->
        if (DeskRepository.isNotExist(param.deskId)) {
            call.respond(
                HttpStatusCode.BadRequest,
                "deskId ${param.deskId} not found"
            )
            return@get
        }
        val itemInfo = ItemInfoRepository.findByItemId(param.itemId) ?: run {
            call.respond(
                HttpStatusCode.BadRequest,
                "itemId ${param.itemId} not found"
            )
            return@get
        }
        if (itemInfo.type is ContentType.File) {
            val file = S3Service.getFile(param.deskId, param.itemId, itemInfo.name)
            call.respondFile(file)
        } else if (itemInfo.type is ContentType.NotFile) {
            val value = ContentRepository.findByItemId(param.itemId) ?: run {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "itemId ${param.itemId} not found"
                )
                return@get
            }
            call.respondText(value)
        }
    }
}