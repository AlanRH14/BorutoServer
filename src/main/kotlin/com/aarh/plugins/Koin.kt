package com.aarh.plugins

import com.aarh.di.koinModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin

fun Application.configurationKoin() {
    startKoin {
        modules(koinModule)
    }
}