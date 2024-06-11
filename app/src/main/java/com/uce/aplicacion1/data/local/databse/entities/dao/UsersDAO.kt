package com.uce.aplicacion1.data.local.databse.entities.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uce.aplicacion1.data.local.databse.entities.UsersDB

@Dao
interface UsersDAO {

    @Query("select * from users")
    fun getAllUsers() : List<UsersDB>

    @Query("select * from users WHERE id_user =:id")
    fun getUserById(id: Int): UsersDB

    @Insert
    fun saveUser(user: List<UsersDB>)

    @Delete
    fun deleteUser(user:UsersDB)

}