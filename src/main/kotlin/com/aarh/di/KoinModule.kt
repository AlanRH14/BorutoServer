package com.aarh.di

import com.aarh.repository.HeroRepository
import com.aarh.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}