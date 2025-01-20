package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero
import com.aarh.utils.AllHeroesObject

class HeroRepositoryImplAlternative : HeroRepositoryAlternative {

    override val heroes: List<Hero>
        get() = AllHeroesObject.heroes

    override suspend fun getAllHeroes(page: Int, limit: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchHeroes(name: String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "Ok",
            heroes = findHeroes(query = name)
        )
    }

    private fun findHeroes(query: String?): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (!query.isNullOrEmpty()) {
            heroes.forEach { hero ->
                if (hero.name.lowercase().contains(query.lowercase())) {
                    founded.add(hero)
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}