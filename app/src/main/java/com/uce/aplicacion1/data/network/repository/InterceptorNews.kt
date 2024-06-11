package com.uce.aplicacion1.data.network.repository

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorNews(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("api_token", apiKey)
            .addQueryParameter("locale", "ec")
            .addQueryParameter("limit", "1")
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)

    }
}