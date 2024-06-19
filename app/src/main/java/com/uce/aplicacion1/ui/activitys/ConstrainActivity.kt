package com.uce.aplicacion1.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.uce.aplicacion1.R
import com.uce.aplicacion1.databinding.ActivityConstrainBinding
import com.uce.aplicacion1.logic.usercase.GetAllTopsNewUserCase
import com.uce.aplicacion1.logic.usercase.GetOneTopNewUserCase
import com.uce.aplicacion1.ui.adapters.NewsAdapter
import com.uce.aplicacion1.ui.adapters.NewsDiffCallback
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.fragments.FavoriteFragment
import com.uce.aplicacion1.ui.fragments.ListarNews
import com.uce.aplicacion1.ui.fragments.MenuBottomSheetFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConstrainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initListeners()

        val x =supportFragmentManager.beginTransaction()
        x.replace(binding.lytFragment.id, ListarNews())
        x.commit()
        true

    }

    private fun initListeners(){

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when (item.itemId){
                R.id.listarItem ->{
                    //Esta es la manera con la cual agregamos un fragment a un layout.
                    val x =supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id, ListarNews())
                    x.addToBackStack(null)
                    x.commit()
                    true
                }
                R.id.favItem->{
                    //Snackbar.make(binding.refreshRV, "Item Favoritos", Snackbar.LENGTH_LONG).show()
                    val x =supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id, FavoriteFragment())
                    x.commit()
                    true
                    true
                }
                R.id.noFavItem->{
                    Snackbar.make(binding.lytFragment, "Item no Fav", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }

        }
        binding.btnClose.setOnClickListener{
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}

//    private fun addItem(){
//        binding.pgbarLoadData.visibility = View.VISIBLE
//        lifecycleScope.launch( Dispatchers.IO){
//            val addNew = GetOneTopNewUserCase().invoke()
//            withContext(Dispatchers.Main){
//                binding.pgbarLoadData.visibility = View.INVISIBLE
//                addNew.onSuccess {
//                    items.add(it)
//                    newsAdapter.listItems=items
//                    newsAdapter.notifyItemInserted(items.size-1)
//                }
//                addNew.onFailure {
//                    Snackbar.make(binding.refreshRV,it.message.toString(),Snackbar.LENGTH_LONG).show()
//                }
//            }
//        }
//
//
//    }

