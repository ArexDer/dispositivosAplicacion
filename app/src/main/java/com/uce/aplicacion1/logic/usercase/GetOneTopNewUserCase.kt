package com.uce.aplicacion1.logic.usercase

import com.uce.aplicacion1.data.network.endpoints.NewsEndpoint
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.entites.core.toNewsDataUI

class GetOneTopNewUserCase {
    suspend operator fun invoke(): Result<NewsDataUI> {
        var response = RetrofitBase.returnBaseRetrofitNewsOne()
            .create(NewsEndpoint::class.java)
            .getAllTopNews()

        var item : NewsDataUI = NewsDataUI(
            "1",
            "www.google.com",
            "Noticia mentira",
            "fjndsfo",
            "Descripcion",
            "ES"
        )
        return if (response.isSuccessful){

            response.body()?.data?.forEach(){
                item = it.toNewsDataUI()
            }
            Result.success(item)
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}