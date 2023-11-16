package com.aarh.utils

import com.aarh.models.Hero

object HeroPag2Object {
    val hero4 = Hero(
        id = 4,
        name = "Boruto",
        image = "/images/boruto.png",
        about = "Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakure's Uzumaki clan and a direct descendant of the Hyūga clan through his mother. While initially resentful of his father and his absence since becoming Hokage, Boruto eventually comes to respect his father and duties.",
        rating = 4.9,
        power = 95,
        month = "Mar",
        day = "27th",
        family = listOf(
            "Naruto",
            "Hinata",
            "Hanabi",
            "Himawari",
            "Kawaki",
        ),
        abilities = listOf(
            "Karma",
            "Jogan",
            "Rasengan",
            "Intelligence",
        ),
        natureTypes = listOf(
            "Lightning",
            "Wind",
            "Water",
        ),
    )

    val hero5 = Hero(
        id = 5,
        name = "Sarada",
        image = "/images/sarada.jpg",
        about = "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakure's Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what she's supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
        rating = 4.9,
        power = 95,
        month = "Mar",
        day = "31st",
        family = listOf(
            "Sasuke Uchiha",
            "Sakura Uchiha",
        ),
        abilities = listOf(
            "Sharingan",
            "Strength",
            "Intelligence",
        ),
        natureTypes = listOf(
            "Lightning",
            "Wind",
            "Fire",
        ),
    )

    val hero6 = Hero(
        id = 6,
        name = "Mitsuki",
        image = "/images/mitsuki.jpg",
        about = "Mitsuki (ミツキ, Mitsuki) is a synthetic human that was created as a partial clone of Orochimaru. Immigrating to Konohagakure to confirm whether or not Boruto Uzumaki was his \"sun\", he became a shinobi and was placed on Team Konohamaru. Mitsuki was created as a clone of Orochimaru, being cultivated from the same embryo as at least one older \"Mitsuki\", and raised in a test tube.",
        rating = 4.9,
        power = 95,
        month = "Jul",
        day = "25th",
        family = listOf(
            "Orochimaru",
            "Log",
        ),
        abilities = listOf(
            "Senin Mode",
            "Transformation",
            "Intelligence",
        ),
        natureTypes = listOf(
            "Lightning",
            "Wind",
        ),
    )
}