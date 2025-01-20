package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero

interface HeroRepositoryAlternative {

    val heroes: List<Hero>

    suspend fun getAllHeroes(page: Int = 1, limit: Int = 4): ApiResponse
    suspend fun searchHeroes(name: String?): ApiResponse
}