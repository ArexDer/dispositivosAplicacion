package com.uce.aplicacion1.data.local.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uce.aplicacion1.data.local.databse.entities.UsersDB
import com.uce.aplicacion1.data.local.databse.entities.dao.UsersDAO

@Database(entities =[UsersDB::class], version = 1)
abstract class DataBaseRepository: RoomDatabase() {

    //Para que me devuelva mi DAO
    abstract fun getUserDao(): UsersDAO




}