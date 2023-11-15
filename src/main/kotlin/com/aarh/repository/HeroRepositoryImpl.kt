package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>>
        get() = TODO("Not yet implemented")
    override val page1: List<String>
        get() = TODO("Not yet implemented")
    override val page2: List<String>
        get() = TODO("Not yet implemented")
    override val page3: List<String>
        get() = TODO("Not yet implemented")
    override val page4: List<String>
        get() = TODO("Not yet implemented")
    override val page5: List<String>
        get() = TODO("Not yet implemented")

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}