package com.uce.aplicacion1.data.network.entities.clash.player

data class CurrentDeck(
    val count: Int,
    val elixirCost: Int,
    val evolutionLevel: Int,
    val iconUrls: IconUrls,
    val id: Int,
    val level: Int,
    val maxEvolutionLevel: Int,
    val maxLevel: Int,
    val name: String,
    val rarity: String,
    val starLevel: Int
)