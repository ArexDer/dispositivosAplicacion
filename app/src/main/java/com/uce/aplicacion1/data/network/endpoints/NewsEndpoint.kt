package com.uce.aplicacion1.data.network.endpoints

import com.uce.aplicacion1.data.network.entities.allNews.AllNews
import com.uce.aplicacion1.data.network.entities.topNews.NewsApi
import retrofit2.Response
import retrofit2.http.GET

interface NewsEndpoint {
    @GET("top")
    suspend fun getAllTopNews(): Response<NewsApi>

    @GET("all")
    suspend fun getAllNews() : Response<AllNews>


}