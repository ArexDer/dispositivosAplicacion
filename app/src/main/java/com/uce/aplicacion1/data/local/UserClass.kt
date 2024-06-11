package com.uce.aplicacion1.data.local

import android.util.Log

class UserClass (
    var id : Int ,
    var name : String ,
    var pass : String
    ){
        init {
            // se crea cuando se intancia la clase
            Log.d("Data","Secpmtruye una nueva instance de usuarios")
        }
}