package com.uce.aplicacion1.data.network.repository

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class MoviesInterceptor() : Interceptor {

        private val key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2OGVjOWEyMGQzODk2ZGJlMmViNTc5NjVjMjM2MzgyNiIsInN1YiI6IjY2NjFkOGQ1ZTU0YjU1MjVhNjdiMzlhNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aQBBKygUoZeAsULF93YnfWEuGGA52PGNVV1OAmqLprw"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request= chain.request()
        val url=request.url.newBuilder().build()


        val headeres = request.headers.newBuilder()
        headeres.add("Authentication","Bears $key")

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)

    }








}