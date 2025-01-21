package com.aarh.routes

import com.aarh.models.ApiResponse
import com.aarh.repository.HeroRepositoryAlternative
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject

fun Route.getAllHeroesAlternative() {
    val heroRepositoryAlternative: HeroRepositoryAlternative by inject(HeroRepositoryAlternative::class.java)

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            val limit = call.request.queryParameters["limit"]?.toInt() ?: 3

            val apiResponse = heroRepositoryAlternative.getAllHeroes(page = page, limit = limit)
            call.respond(
                message = apiResponse,
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Only Numbers Allowed."
                ),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = "Heroes not Found."
                ),
                status = HttpStatusCode.NotFound
            )
        }
    }
}