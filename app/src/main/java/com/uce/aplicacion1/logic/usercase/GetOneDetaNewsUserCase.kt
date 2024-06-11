package com.uce.aplicacion1.logic.usercase

import com.uce.aplicacion1.data.network.endpoints.NewsEndpoint
import com.uce.aplicacion1.data.network.endpoints.UUIDNews
import com.uce.aplicacion1.data.network.entities.topNews.oneNews.OneNewsDataClass
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.entites.core.toNewsDataUI

class GetOneDetaNewsUserCase {
    suspend operator fun invoke(uuid :String): Result<OneNewsDataClass?> {
        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)
        return if (response.isSuccessful){

            Result.success(response.body())
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}