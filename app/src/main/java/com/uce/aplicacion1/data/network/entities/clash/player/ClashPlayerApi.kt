package com.uce.aplicacion1.data.network.entities.clash.player

data class ClashPlayerApi(
    val battleCount: Int,
    val bestTrophies: Int,
    val challengeCardsWon: Int,
    val challengeMaxWins: Int,
    val clan: Clan,
    val clanCardsCollected: Int,
    val currentDeck: List<CurrentDeck>,
    val currentDeckSupportCards: List<CurrentDeckSupportCard>,
    val currentFavouriteCard: CurrentFavouriteCard,
    val donations: Int,
    val donationsReceived: Int,
    val expLevel: Int,
    val losses: Int,
    val name: String,
    val role: String,
    val tag: String,
    val threeCrownWins: Int,
    val totalDonations: Int,
    val tournamentBattleCount: Int,
    val tournamentCardsWon: Int,
    val trophies: Int,
    val warDayWins: Int,
    val wins: Int
)