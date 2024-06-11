package com.uce.aplicacion1.data.network.endpoints.movies

import com.uce.aplicacion1.data.network.entities.allNews.AllNews
import com.uce.aplicacion1.data.network.entities.movies.moviesDetail
import com.uce.aplicacion1.data.network.entities.topNews.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesEndpoint {

    @GET("/movies/{movie_id}")
    suspend fun getDetailsMovies(
        @Path("movie_id")movieId:Int):
            Response<moviesDetail?>
       // @Query("limit")limit:Int,
        //@Query("limit")limit:Int =3,


}