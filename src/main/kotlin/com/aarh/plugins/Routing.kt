package com.aarh.plugins

import com.aarh.routes.getAllHeroesAlternative
import com.aarh.routes.root
import com.aarh.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        //getAllHeroes()
        getAllHeroesAlternative()
        searchHeroes()
        staticResources(remotePath = "/", basePackage = "", index = "image") {
            enableAutoHeadResponse()
        }
    }
}
