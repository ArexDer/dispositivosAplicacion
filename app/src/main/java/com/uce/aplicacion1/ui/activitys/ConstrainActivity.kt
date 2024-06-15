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
import com.uce.aplicacion1.ui.fragments.ListarNews
import com.uce.aplicacion1.ui.fragments.MenuBottomSheetFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConstrainBinding
    private var items : MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnInsert.setOnClickListener {
            addItem()
        }
        binding.buttonOpenBottomSheet.setOnClickListener {
            showMenuBottomSheet()
        }

        initVariables()
        initListeners()
        initData()

        //Esta es la manera con la cual agregamos un fragment a un layout.
        val x =supportFragmentManager.beginTransaction()
        x.replace(binding.lytFragment.id, ListarNews())
        x.commit()

    }

    private fun initVariables() {
        newsAdapter = NewsAdapter(
            {descriptionItem(it)},
            {deleteItem(it)},
            {addItem()})

        binding.rvTopNews.adapter = newsAdapter
        binding.rvTopNews.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )

        binding.rvTopNews.layoutManager = CarouselLayoutManager()
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                deleteItem(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvTopNews)
    }


    private fun initListeners(){
        binding.refreshRV.setOnRefreshListener{
            initData()
            binding.refreshRV.isRefreshing = false
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.listarItem ->{
                    startActivity(Intent(this, DetailItemActivity::class.java))
                    true
                }
                R.id.favItem->{
                    Snackbar.make(binding.refreshRV, "Item Favoritos", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.noFavItem->{
                    Snackbar.make(binding.refreshRV, "Item no Fap", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun initData(){
        binding.pgbarLoadData.visibility = View.VISIBLE
        lifecycleScope.launch( Dispatchers.IO){

            val result = GetAllTopsNewUserCase().invoke()
            withContext(Dispatchers.Main){
                binding.pgbarLoadData.visibility = View.INVISIBLE
                result.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.listItems=items
                    newsAdapter.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
                }
                result.onFailure {
                    Snackbar.make(binding.refreshRV,it.message.toString(),Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun descriptionItem(news: NewsDataUI){

        Snackbar.make(binding.refreshRV, news.name, Snackbar.LENGTH_LONG).show()
        /*val intent = Intent(
            this,
            DetailItemActivity::class.java
        ).apply {
            putExtra("id", news.id)
        }
        startActivity(intent)

         */
    }
    private fun deleteItem(position: Int){
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        val newList = items.toMutableList().apply { removeAt(position) }
        val diffCallback = NewsDiffCallback(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = newList
        newsAdapter.listItems = items
        diffResult.dispatchUpdatesTo(newsAdapter)
    }

//    private fun deleteItem(position: Int){
//        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
//        items.removeAt(position)
//        newsAdapter.listItems = items
//        newsAdapter.notifyItemRemoved(position)
//    }



    private fun addItem(){
        binding.pgbarLoadData.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO){
            val addNew = GetOneTopNewUserCase().invoke()
            withContext(Dispatchers.Main){
                binding.pgbarLoadData.visibility = View.INVISIBLE
                addNew.onSuccess { newItem ->
                    val newList = items.toMutableList().apply { add(newItem) }
                    val diffCallback = NewsDiffCallback(items, newList)
                    val diffResult = DiffUtil.calculateDiff(diffCallback)

                    items = newList
                    newsAdapter.listItems = items
                    diffResult.dispatchUpdatesTo(newsAdapter)
                }
                addNew.onFailure {
                    Snackbar.make(binding.refreshRV, it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun showMenuBottomSheet() {
        val menuBottomSheetFragment = MenuBottomSheetFragment()
        menuBottomSheetFragment.show(supportFragmentManager, menuBottomSheetFragment.tag)
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

