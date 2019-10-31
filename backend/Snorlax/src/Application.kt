package com.yt8492

import com.yt8492.infra.db.table.Contents
import com.yt8492.infra.db.table.DeskIds
import com.yt8492.infra.db.table.ItemInfos
import com.yt8492.router.deskRoute
import com.yt8492.router.itemRoute
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.*
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.get
import io.ktor.routing.options
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseHelper.init()

    transaction {
        SchemaUtils.create(Contents, DeskIds, ItemInfos)
    }

    install(CallLogging)

    install(Locations)

    install(CORS) {
        anyHost()
        allowSameOrigin = false
        method(HttpMethod.Put)
    }

    install(ContentNegotiation) {
        serialization()
    }

    routing {
        get("/health") {
            call.respond(HttpStatusCode.OK, "success")
        }
        deskRoute()
        itemRoute()
    }
}
