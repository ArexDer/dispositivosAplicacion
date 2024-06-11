package com.uce.aplicacion1.ui.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.uce.aplicacion1.R
import com.uce.aplicacion1.data.network.entities.topNews.oneNews.OneNewsDataClass
import com.uce.aplicacion1.databinding.ActivityDetailItemBinding
import com.uce.aplicacion1.logic.usercase.GetOneDetaNewsUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailItemBinding
    private var itemId: String = ""
    private var fecha: String =""
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras.let {
            itemId = it?.getString("id").toString()
            fecha = it?.getString("published_at").orEmpty()
            url = it?.getString("url").orEmpty()
        }

        binding.txtIdItem.text = itemId

        lifecycleScope.launch(Dispatchers.Main) {
            val item = withContext(Dispatchers.IO){
                getData()
            }
            if (item != null){
                binding.txtIdItem.text = item.title
                binding.txtDate.setText(formatPublishedDate(item.published_at))
                binding.txtSnippet.text = item.snippet
                binding.txtLocale.text = item.locale
                binding.txtCategory.setText(categorryFormat(item.categories))
                binding.imageView
                    .load(item.image_url) {
                        placeholder(R.drawable.clarin)
                    }

            }
        }
    }
    suspend fun getData(): OneNewsDataClass?{
        var item : OneNewsDataClass? = null
        val x = GetOneDetaNewsUserCase().invoke(itemId)
        x.onSuccess {
            item = it
        }
        x.onFailure {
            Log.d("API","El llamado a la API FALLO")
        }
        return item
    }

    private fun formatPublishedDate(dateStr: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
            val date = inputFormat.parse(dateStr)
            date?.let { outputFormat.format(it) } ?: "Fecha no disponible"
        } catch (e: Exception) {
            "Fecha no disponible"
        }
    }

    private fun categorryFormat(categorias: List<String>): String{
        return try {
            val inputFormat = categorias
            var outputFormat = "Categoría: "
            categorias.forEach(){
                outputFormat = outputFormat + it + "/"
            }
            outputFormat
        } catch (e: Exception) {
            "Categoria no disponible"
        }
    }
}