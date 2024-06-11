package com.uce.aplicacion1.data.network.entities.clash.cards

data class ClashCardsApi(
    val items: List<Item>,
    val supportItems: List<SupportItem>
)