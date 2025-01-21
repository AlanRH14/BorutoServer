package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero
import com.aarh.utils.AllHeroesObject

class HeroRepositoryImplAlternative : HeroRepositoryAlternative {

    override val heroes: List<Hero>
        get() = AllHeroesObject.heroes

    override suspend fun getAllHeroes(page: Int, limit: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "Ok",
            prevPage = calculatePage(
                heroes = heroes,
                page = page,
                limit = limit
            )["prevPage"],
            nextPage = calculatePage(
                heroes = heroes,
                page = page,
                limit = limit
            )["nextPage"],
            heroes = provideHeroes(
                heroes = heroes,
                page = page,
                limit = limit
            ),
            lastUpdated = System.currentTimeMillis()
        )
    }

    override suspend fun searchHeroes(name: String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "Ok",
            heroes = findHeroes(query = name)
        )
    }

    private fun calculatePage(
        heroes: List<Hero>,
        page: Int,
        limit: Int
    ): Map<String, Int?> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true,
        )
        require(page <= allHeroes.size)
        val prevPage = if (page == 1) null else page - 1
        val nextPage = if (page >= allHeroes.size) null else page + 1

        return mapOf(
            "prevPage" to prevPage,
            "nextPage" to nextPage
        )
    }

    private fun provideHeroes(
        heroes: List<Hero>,
        page: Int,
        limit: Int,
    ): List<Hero> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true
        )
        require(page > 0 && page <= allHeroes.size)
        return allHeroes[page - 1]
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