package com.aarh

import com.aarh.plugins.*
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configurationKoin()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configurationDefaultHeader()
    configurationStatusPages()
}
