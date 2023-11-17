package com.aarh

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        val response = client.get("/")

        client.get("/").apply {
            assertEquals(expected = HttpStatusCode.OK, actual = response.status)
            assertEquals(expected = "Welcome to Boruto API!", actual = response.bodyAsText())
        }
    }
}
