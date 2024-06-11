package com.uce.aplicacion1.logic.usercase

import android.util.Log
import com.uce.aplicacion1.data.network.endpoints.UsersEndpoint
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllUsersUserCase {
    suspend operator fun  invoke() {

        withContext(Dispatchers.IO){

            val call  = RetrofitBase.returnBaseRetrofit()
            val service = call.create(UsersEndpoint::class.java)
            val response = service.getAllUsers()

            return@withContext if(response.isSuccessful){
                val body = response.body()
                Log.d("RSP", body.toString())
                true
            }else{
                Log.d("RSP", "La ejecucion fallo ")
                false
            }
        }
    }
}