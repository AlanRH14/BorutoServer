package com.aarh

import com.aarh.models.ApiResponse
import com.aarh.repository.HeroRepositoryImpl
import com.aarh.utils.NEXT_PAGE_KEY
import com.aarh.utils.PREVIOUS_PAGE_KEY
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.*

class ApplicationTest {
    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        val response = client.get("/")

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = "Welcome to Boruto API!",
            actual = response.bodyAsText(),
        )
    }

    @Test
    fun `access all heroes endpoint, query all pages, assert correct information`() = testApplication {
        val heroRepository = HeroRepositoryImpl()
        val pages = 1..5
        val heroes = listOf(
            heroRepository.page1,
            heroRepository.page2,
            heroRepository.page3,
            heroRepository.page4,
            heroRepository.page5,
        )

        pages.forEach { page ->
            val response = client.get("/boruto/heroes?page=$page")
            val expected = ApiResponse(
                success = true,
                message = "Ok",
                prevPage = calculatePage(page = page)[PREVIOUS_PAGE_KEY],
                nextPage = calculatePage(page = page)[NEXT_PAGE_KEY],
                heroes = heroes[page - 1],
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
            println("CURRENT PAGE: $page")
            println("PREVIOUS PAGE: ${calculatePage(page)[PREVIOUS_PAGE_KEY]}")
            println("NEXT PAGE: ${calculatePage(page)[NEXT_PAGE_KEY]}")
            println("HEROES: ${heroes[page - 1]}")

            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status,
            )
            assertEquals(
                expected = actual,
                actual = expected,
            )
        }
    }

    @Test
    fun `access all heroes endpoint, assert correct information`() = testApplication {
        val heroRepository = HeroRepositoryImpl()
        val response = client.get("/boruto/heroes")
        val expected = ApiResponse(
            success = true,
            message = "Ok",
            prevPage = null,
            nextPage = 2,
            heroes = heroRepository.page1,
        )
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
        println("EXPECTED: $expected")
        println("ACTUAL: $actual")

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `access all heroes endpoint, query second page, assert correct information`() = testApplication {
        val heroRepository = HeroRepositoryImpl()
        val response = client.get("/boruto/heroes?page=2")
        val expected = ApiResponse(
            success = true,
            message = "Ok",
            prevPage = 1,
            nextPage = 3,
            heroes = heroRepository.page2,
        )
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        assertEquals(
            actual = HttpStatusCode.OK,
            expected = response.status,
        )
        assertEquals(
            actual = expected,
            expected = actual,
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page

        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }

        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }

        if (page == 1) {
            prevPage = null
        }

        if (page == 5) {
            nextPage = null
        }

        return mapOf(
            PREVIOUS_PAGE_KEY to prevPage,
            NEXT_PAGE_KEY to nextPage,
        )
    }
}
