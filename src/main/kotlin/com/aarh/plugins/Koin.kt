package com.aarh.plugins

import com.aarh.di.koinModule
import io.ktor.server.application.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger

fun Application.configurationKoin() {
    startKoin {
        slf4jLogger()
        modules(koinModule)
    }
}