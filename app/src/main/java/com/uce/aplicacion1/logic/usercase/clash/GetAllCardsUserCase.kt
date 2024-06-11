package com.uce.aplicacion1.logic.usercase.clash

import com.uce.aplicacion1.data.network.endpoints.clash.ClashEndPoint
import com.uce.aplicacion1.data.network.entities.clash.cards.ClashCardsApi
import com.uce.aplicacion1.data.network.entities.clash.cards.Item
import com.uce.aplicacion1.data.network.repository.RetrofitBase

class GetAllCardsUserCase {
    suspend operator fun invoke(): Result<List<Item>>{
        var response = RetrofitBase.returnBaseRetrofitClash()
            .create(ClashEndPoint::class.java)
            .getCardsInfo()

        var items = mutableListOf<Item>()

        return if (response.isSuccessful){
            //el body nos devuelve la data
            response.body()?.items?.forEach(){
                items.add(
                    it
                )
            }
            Result.success(items.toList())
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}