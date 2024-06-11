package com.uce.aplicacion1.logic.usercase.clash

import com.uce.aplicacion1.data.network.endpoints.NewsEndpoint
import com.uce.aplicacion1.data.network.endpoints.clash.ClashEndPoint
import com.uce.aplicacion1.data.network.entities.clash.player.ClashPlayerApi
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.entites.core.toNewsDataUI

class GetPlayerClashUserCase {
    suspend operator fun invoke(idTagClash: String): Result<ClashPlayerApi?>{
        var response = RetrofitBase.returnBaseRetrofitClash()
            .create(ClashEndPoint::class.java)
            .getPlayerInfo(idTagClash)

        return if (response.isSuccessful){
            //el body nos devuelve la data
            Result.success(response.body())
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}