package com.uce.aplicacion1.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uce.aplicacion1.data.local.repository.ListUsers
import com.uce.aplicacion1.databinding.ActivityMainBinding
import com.uce.aplicacion1.logic.usercase.LoginUserCase

class  MainActivity : AppCompatActivity() {

    //private lateinit var etxUser : EditText

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {

        binding.btnLogin.setOnClickListener {

            val loginUserCase = LoginUserCase(ListUsers())

            val result = loginUserCase(
                binding.etxUser.text.toString(),
                binding.etxPass.text.toString()
            )

            result.onSuccess { user ->
                val intentToConstarintAct = Intent(
                    this,
                    ConstrainActivity::class.java
                ).apply {
                    putExtra("idUser", user.id)
                }
                startActivity(intentToConstarintAct)
            }

            result.onFailure {
                Toast.makeText(
                    this,
                    it.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("UCE", "Metodo Star")
    }

    override fun onResume() {
        super.onResume()

        Log.d("UCE", "Metodo Resume")
    }
}




