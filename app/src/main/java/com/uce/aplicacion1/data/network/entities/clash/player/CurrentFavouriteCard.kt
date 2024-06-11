package com.uce.aplicacion1.data.network.entities.clash.player

data class CurrentFavouriteCard(
    val elixirCost: Int,
    val iconUrls: IconUrlsX,
    val id: Int,
    val maxLevel: Int,
    val name: String,
    val rarity: String
)