package com.aarh.routes

import com.aarh.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject(HeroRepository::class.java)

    get("/boruto/hero/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = heroRepository.searchHeroes(name = name)

        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK,
        )
    }
}