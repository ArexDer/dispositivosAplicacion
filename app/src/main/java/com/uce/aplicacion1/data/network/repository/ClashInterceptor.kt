package com.uce.aplicacion1.data.network.repository

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ClashInterceptor(private val apiKey: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        Log.d("TAG", newUrl.url.toString())
        return chain.proceed(newUrl)
    }

}