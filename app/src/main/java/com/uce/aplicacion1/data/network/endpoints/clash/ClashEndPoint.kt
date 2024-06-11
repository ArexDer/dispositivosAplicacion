package com.uce.aplicacion1.data.network.endpoints.clash

import com.uce.aplicacion1.data.network.entities.clash.cards.ClashCardsApi
import com.uce.aplicacion1.data.network.entities.clash.player.ClashPlayerApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ClashEndPoint {
    @GET("player/{playerTag}")
    suspend fun getPlayerInfo(@Path("playerTag") playerTag: String): Response<ClashPlayerApi>

    @GET("cards")
    suspend fun getCardsInfo(): Response<ClashCardsApi>
}