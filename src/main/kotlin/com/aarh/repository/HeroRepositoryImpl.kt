package com.aarh.repository

import com.aarh.models.ApiResponse
import com.aarh.models.Hero
import com.aarh.utils.HeroPag1Object.hero1
import com.aarh.utils.HeroPag1Object.hero2
import com.aarh.utils.HeroPag1Object.hero3
import com.aarh.utils.HeroPag2Object.hero4
import com.aarh.utils.HeroPag2Object.hero5
import com.aarh.utils.HeroPag2Object.hero6
import com.aarh.utils.HeroPag3Object.hero7
import com.aarh.utils.HeroPag3Object.hero8
import com.aarh.utils.HeroPag3Object.hero9
import com.aarh.utils.HeroPag4Object.hero10
import com.aarh.utils.HeroPag4Object.hero11
import com.aarh.utils.HeroPag4Object.hero12
import com.aarh.utils.HeroPag5Object.hero13
import com.aarh.utils.HeroPag5Object.hero14
import com.aarh.utils.HeroPag5Object.hero15
import com.aarh.utils.NEXT_PAGE_KEY
import com.aarh.utils.PREVIOUS_PAGE_KEY

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5,
        )
    }

    override val page1: List<Hero> = listOf(
        hero1,
        hero2,
        hero3,
    )

    override val page2: List<Hero> = listOf(
        hero4,
        hero5,
        hero6,
    )

    override val page3: List<Hero> = listOf(
        hero7,
        hero8,
        hero9,
    )
    override val page4: List<Hero> = listOf(
        hero10,
        hero11,
        hero12,
    )
    override val page5: List<Hero> = listOf(
        hero13,
        hero14,
        hero15,
    )

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "Ok",
            prevPage = calculatePage(page = page)[PREVIOUS_PAGE_KEY],
            nextPage = calculatePage(page = page)[NEXT_PAGE_KEY],
            heroes = heroes[page] ?: emptyList(),
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page

        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }

        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }

        if (page == 1) {
            prevPage = null
        }

        if (page == 5) {
            nextPage = null
        }

        return mapOf(
            PREVIOUS_PAGE_KEY to prevPage,
            NEXT_PAGE_KEY to nextPage,
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}