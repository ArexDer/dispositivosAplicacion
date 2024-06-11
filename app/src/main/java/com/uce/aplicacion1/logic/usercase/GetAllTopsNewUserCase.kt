package com.uce.aplicacion1.logic.usercase

import com.uce.aplicacion1.data.network.endpoints.NewsEndpoint
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.entites.core.toNewsDataUI

class GetAllTopsNewUserCase {

    suspend operator fun invoke(): Result<List<NewsDataUI>>{
        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopNews()

        var items = mutableListOf<NewsDataUI>()

        return if (response.isSuccessful){
            //el body nos devuelve la data
            response.body()?.data?.forEach(){
                items.add(
                    it.toNewsDataUI()
                )
            }
            Result.success(items.toList())
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}