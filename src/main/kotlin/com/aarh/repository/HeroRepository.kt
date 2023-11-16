package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero

interface HeroRepository {
    val heroes: Map<Int, List<Hero>>
    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun getAllHeroes(page: Int = 1): ApiResponse

    suspend fun searchHeroes(name: String): ApiResponse
}