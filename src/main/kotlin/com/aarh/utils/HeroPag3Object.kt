package com.aarh.utils

import com.aarh.models.Hero

object HeroPag3Object {
    val hero7 = Hero(
        id = 7,
        name = "Kawaki",
        image = "/images/kawaki.jpg",
        about = "Kawaki (カワキ, Kawaki) is a child raised by Kara to be the future vessel for Isshiki Ōtsutsuki and the key to the fulfilment of their greatest wish.[1] After being brought to Konohagakure by Team 7, he is taken in by Naruto Uzumaki who raises him as his own, during which he develops a brotherly bond with Boruto Uzumaki to solve the mystery of the Kāma.",
        rating = 4.2,
        power = 92,
        month = "Jan",
        day = "1st",
        family = listOf(
            "Kokatsu",
        ),
        abilities = listOf(
            "Karma",
            "Transformation",
            "Strength",
        ),
        natureTypes = listOf(
            "Fire",
        ),
    )

    val hero8 = Hero(
        id = 8,
        name = "Orochimaru",
        image = "/images/orochimaru.jpg",
        about = "Orochimaru (大蛇丸, Orochimaru) is one of Konohagakure's legendary Sannin. With a life-ambition to learn all of the world's secrets, Orochimaru seeks immortality so that he might live all of the lives necessary to accomplish his task. After being caught red-handed performing unethical experiments on his fellow citizens for the sake of this immortality, Orochimaru defected from Konoha.",
        rating = 4.5,
        power = 97,
        month = "Oct",
        day = "27th",
        family = listOf(
            "Mitsuki",
            "Log",
        ),
        abilities = listOf(
            "Senin Mode",
            "Transformation",
            "Science",
        ),
        natureTypes = listOf(
            "Lightning",
            "Wind",
            "Fire",
            "Earth",
            "Water",
        ),
    )

    val hero9 = Hero(
        id = 9,
        name = "Kakashi",
        image = "/images/kakashi.png",
        about = "Kakashi Hatake (はたけカカシ, Hatake Kakashi) is a shinobi of Konohagakure's Hatake clan. Famed as Kakashi of the Sharingan (写輪眼のカカシ, Sharingan no Kakashi), he is one of Konoha's most talented ninja, regularly looked to for advice and leadership despite his personal dislike of responsibility. To his students on Team 7, Kakashi emphasises the importance of teamwork; he himself received this lesson, along with the Sharingan, from his childhood friend, Obito Uchiha.",
        rating = 4.5,
        power = 96,
        month = "Sep",
        day = "15th",
        family = listOf(
            "Sakumo",
        ),
        abilities = listOf(
            "Intelligence",
            "Strength",
        ),
        natureTypes = listOf(
            "Lightning",
            "Wind",
            "Fire",
            "Earth",
            "Water",
        ),
    )
}