package com.uce.aplicacion1.data.local.databse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
 data class UsersDB (
     @PrimaryKey(autoGenerate = true) //Con esto se me autogenera el id
     @ColumnInfo(name = "id_user")
     val id: Int=0,         //NO ES NULEABLE SI QUIERO LO ENVIO SINO NO.
     @ColumnInfo(name = "name_user")
     val name: String?,
     @ColumnInfo(name = "password_user")
     val password:String?
     )