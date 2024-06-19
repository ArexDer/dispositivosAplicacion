package com.uce.aplicacion1.ui.activitys

import kotlinx.coroutines.withContext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope

import com.uce.aplicacion1.databinding.ActivityBiometricBinding
import com.uce.aplicacion1.ui.entites.DataStoreEntitie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

//Como variabe de DataStore  PARA QEU SEA DE CNTEXTO GLOBAL
val Context.setttingDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class BiometricActivity : AppCompatActivity() {

    //BINDING DEL ACTIVITY
    private lateinit var binding: ActivityBiometricBinding

    //Biblioteca Ejecutor, proceso que se mete a cosas del sistema.
    private lateinit var executor: Executor


    //Es la comprobacion de si existe o no el biometrico
    private lateinit var biometricPrompt: BiometricPrompt

    //Aquel PopUp de la huella
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var biometricManager :BiometricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setContentView(R.layout.activity_biometric)
        val splash = installSplashScreen()

        binding= ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtirne el MASTER del sistema
        executor = ContextCompat.getMainExecutor(this)




        //VARIABLE YA CREADA, maneja el PATRON BILDER
        //ESTE ES EL OBJETO QUE APARECE
        promptInfo=BiometricPrompt.PromptInfo.Builder()
            .setTitle("Mi aplicacion/ My Application")
            .setSubtitle("Ingrese su huella")
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback(){

                //Cuando falla
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                //Es Correcta
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    /*
                    AQUI PONER MI CODIGO PARA QUE SE EJECUTE LAS ACTIVITYS Y PUEDO PONERLE UN TOAST
                     */

                    val x= Intent(applicationContext, MainActivity::class.java)
                    startActivity(x)


                }

                //Existe algun error
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }
            }
        )
        initListeners()
        initListeners()

        Thread.sleep(3000)
        splash.setKeepOnScreenCondition{false}



    }

    private fun initListeners() {

        binding.imgFinger.setOnClickListener{
            //Primero guardar info y luego recuperar en vez del Biometriuco
            dataStoreSave(DataStoreEntitie("Diego",true))

            Log.d("TAG",dataStoreGet().toString())
            //initBiometric()
        }
    }

    fun initBiometric(){
        //Usando la bibloiteca que nos permite acceder al biometrico.
        val biometricManager = BiometricManager.from(this)

        //Aqui pedimos la autentificacion--> LOS niveles String, Weak or Creddential.

        val x =biometricManager.canAuthenticate(
            BiometricManager.Authenticators.DEVICE_CREDENTIAL or
                    BiometricManager.Authenticators.BIOMETRIC_STRONG
        )

        when(x){
            BiometricManager.BIOMETRIC_SUCCESS->{

                //Si ya lo tenog aqui si muestro la pantalla del sistema donde me pide la huella digital
                //La pantalla nos da el sistema. ya que cada telefono tieen en distintas partes
                // su biometrico.

                biometricPrompt.authenticate(promptInfo)

            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->{}
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED->{}
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{

                // Aqui vamos a pedir que ingrese la HUELLA y si no tengo ni pin ni huella me pide el PIN.
                //Es un INTENT explicito, que me llama al sistema, autentificacion, se pida esos dos tipos.

                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG or
                                BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    )
                }
                //Lo corremos con el Start
                startActivity(enrollIntent)
                // startActivityForResult(enrollIntent, REQUEST_CODE)
            }
        }

    }

    private fun dataStoreSave(user: DataStoreEntitie){
        // Guardar
        lifecycleScope.launch(Dispatchers.IO) {
            setttingDataStore.edit{ prefs ->
                prefs[booleanPreferencesKey("active")]=user.active
                prefs[stringPreferencesKey("user")]=user.name
            }
        }

    }

    private fun dataStoreGet():DataStoreEntitie{
        //Recuperar
        var ret =DataStoreEntitie()
        lifecycleScope.launch(Dispatchers.Main) {
            val x = withContext(Dispatchers.IO){
                setttingDataStore.data.map {prefs ->
                    DataStoreEntitie(
                        prefs[stringPreferencesKey(name = "user")] ?: "",
                        prefs[booleanPreferencesKey(name = "active")] ?: false
                    )

                }
            }
            ret =x.first()
            /*
            LO USO CUANDO TENOG UN FLUJO DE DATOS
            UN EJEMPLO PUEDE SER WHATSU

            x.collect{
                ret =it
            }
            */


        }
        return ret!!
    }


    /*
    //PARA RECUPERAR EL BOOLEANO
    private fun dataStoreGetBol():Boolean{
        //Recuperar
        var ret =false
        lifecycleScope.launch(Dispatchers.Main) {
            val x = withContext(Dispatchers.IO){
                setttingDataStore.data.map {prefs ->
                    prefs[booleanPreferencesKey(name = "active")] ?: false
                }
            }
            ret =x.first()
            /*
            LO USO CUANDO TENOG UN FLUJO DE DATOS
            UN EJEMPLO PUEDE SER WHATSU

            x.collect{
                ret =it
            }
            */


        }
        return ret
    }
    */

}



