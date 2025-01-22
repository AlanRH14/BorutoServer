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
import org.koin.core.context.stopKoin
import kotlin.test.*

class ApplicationTest {
    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        application {
            module()
        }
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
        application {
            module()
        }
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
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
            val expected = ApiResponse(
                success = true,
                message = "Ok",
                prevPage = calculatePage(page = page)[PREVIOUS_PAGE_KEY],
                nextPage = calculatePage(page = page)[NEXT_PAGE_KEY],
                heroes = heroes[page - 1],
                lastUpdated = actual.lastUpdated,
            )
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
    fun `access all heroes endpoint, query non existing page number, assert error`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes?page=6")
        assertEquals(
            expected = HttpStatusCode.NotFound,
            actual = response.status,
        )
        assertEquals(
            expected = "404: Page not Found.",
            actual = response.bodyAsText(),
        )
    }

    @Test
    fun `access all heroes endpoint, query invalid page number, assert error`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes?page=invalid")
        val expected = ApiResponse(
            success = false,
            message = "Only Numbers Allowed.",
        )
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
        assertEquals(
            expected = HttpStatusCode.BadRequest,
            actual = response.status,
        )
        assertEquals(
            expected = expected,
            actual = actual,
        )
    }

    @Test
    fun `assert search heroes endpoint, query hero name, assert single hero result`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes/search?name=sas")
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText()).heroes.size

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = 1,
            actual = actual,
        )
    }

    @Test
    fun `assert search heroes endpoint, query hero name, assert multiple heroes result`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes/search?name=sa")
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText()).heroes.size

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = 3,
            actual = actual,
        )
    }

    @Test
    fun `assert search heroes endpoint, query an empty text, assert empty list as a result`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes/search?name=")
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText()).heroes

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = emptyList(),
            actual = actual,
        )
    }

    @Test
    fun `assert search heroes endpoint, query non existing hero, assert empty list as a result`() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes/search?name=unknown")
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText()).heroes

        assertEquals(
            expected = HttpStatusCode.OK,
            actual = response.status,
        )
        assertEquals(
            expected = emptyList(),
            actual = actual,
        )
    }

    @Test
    fun `access non existing endpoint, assert not found`() = testApplication {
        application {
            module()
        }
        val response = client.get("/unknown")
        assertEquals(
            expected = HttpStatusCode.NotFound,
            actual = response.status,
        )
        assertEquals(
            expected = "404: Page not Found.",
            actual = response.bodyAsText(),
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> =
        mapOf(
            PREVIOUS_PAGE_KEY to if (page in 2..5) page.minus(1) else null,
            NEXT_PAGE_KEY to if (page in 1..4) page.plus(1) else null
        )

    @AfterTest
    fun `after init`() {
        stopKoin()
    }
}
