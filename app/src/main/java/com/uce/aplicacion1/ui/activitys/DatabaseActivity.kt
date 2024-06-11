package com.uce.aplicacion1.ui.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.uce.aplicacion1.R
import com.uce.aplicacion1.data.local.databse.entities.UsersDB
import com.uce.aplicacion1.data.local.repository.DataBaseRepository
import com.uce.aplicacion1.databinding.ActivityDatabaseBinding
import com.uce.aplicacion1.ui.entites.core.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseBinding

    private lateinit var conn: DataBaseRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()

    }

    private fun initListeners() {
        binding.button.setOnClickListener {
            val user = binding.editUser.text.toString()
            val pass = binding.editPassword.text.toString()

            val usersDB =UsersDB(name=user, password=pass)
            conn.getUserDao().saveUser(listOf(usersDB))

            lifecycleScope.launch(Dispatchers.IO) {
                conn.getUserDao().saveUser(listOf(usersDB))
            }
        }
    }

    private fun initVariables() {
        conn = MyApplication.getDBConnection()
    }
}