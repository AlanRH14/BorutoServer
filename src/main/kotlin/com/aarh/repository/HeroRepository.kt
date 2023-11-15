package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero

interface HeroRepository {
    val heroes: Map<Int, List<Hero>>
    val page1: List<String>
    val page2: List<String>
    val page3: List<String>
    val page4: List<String>
    val page5: List<String>

    suspend fun getAllHeroes(page: Int = 1): ApiResponse

    suspend fun searchHeroes(name: String): ApiResponse
}