package com.uce.aplicacion1.data.network.entities.clash.player

data class CurrentDeckSupportCard(
    val count: Int,
    val iconUrls: IconUrlsX,
    val id: Int,
    val level: Int,
    val maxLevel: Int,
    val name: String,
    val rarity: String
)