package com.yt8492.router

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.routing.Route
import io.ktor.routing.put

@KtorExperimentalLocationsAPI
fun Route.deskRoute() {
    put("/desk") {

    }

    @Location("/desk/{deskId}")
    data class DeskRequest(val deskId: String)
    post<DeskRequest> {

    }

    get<DeskRequest> {

    }

    @Location("/desk/{deskId}/{itemId}")
    data class ItemRequest(val deskId: String, val itemId: String)
    get<ItemRequest> {
        
    }
}