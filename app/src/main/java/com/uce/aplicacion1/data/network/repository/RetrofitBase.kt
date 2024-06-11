package com.uce.aplicacion1.data.network.repository

import com.uce.aplicacion1.data.network.entities.movies.moviesDetail
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    //CREACION DE LA BASE URL DE TMBD MOVIES
    fun returnBaseRetrofitMovies(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiMovies())
            .build()
    }
    private fun apiMovies():  OkHttpClient = OkHttpClient.Builder().addInterceptor(MoviesInterceptor()).build()



    fun returnBaseRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun returnBaseRetrofitNews(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiCliente())
            .build()
    }
    //API CLASE
    fun returnBaseRetrofitNewsOne(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClienteOne())
            .build()
    }


    private fun apiCliente() : OkHttpClient = OkHttpClient.Builder().addInterceptor(NewsInterceptor(ApiKeys.API_NEWS)).build()
    private fun apiClienteOne() : OkHttpClient = OkHttpClient.Builder().addInterceptor(InterceptorNews(ApiKeys.API_NEWS)).build()


    //API CLASH
    fun returnBaseRetrofitClash(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.clashroyale.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClienteClash())
            .build()
    }

    private fun apiClienteClash() : OkHttpClient = OkHttpClient.Builder().addInterceptor(ClashInterceptor(ApiKeys.API_CLASH)).build()



}