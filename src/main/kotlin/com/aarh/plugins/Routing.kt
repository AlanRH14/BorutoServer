package com.aarh.plugins

import com.aarh.routes.getAllHeroes
import com.aarh.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
    }
}
