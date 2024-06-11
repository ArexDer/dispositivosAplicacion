package com.uce.aplicacion1.data.network.entities.clash.cards

data class Item(
    val elixirCost: Int,
    val iconUrls: IconUrls,
    val id: Int,
    val maxEvolutionLevel: Int,
    val maxLevel: Int,
    val name: String,
    val rarity: String
)