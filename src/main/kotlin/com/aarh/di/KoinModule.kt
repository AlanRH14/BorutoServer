package com.aarh.di

import com.aarh.repository.HeroRepository
import com.aarh.repository.HeroRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinModule = module {
    /*single<HeroRepository> {
        HeroRepositoryImpl()
    }*/
    singleOf(::HeroRepositoryImpl) { bind<HeroRepository>() }
}