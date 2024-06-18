package com.uce.aplicacion1.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.uce.aplicacion1.R
import com.uce.aplicacion1.databinding.FragmentListarNewsBinding
import com.uce.aplicacion1.logic.usercase.GetAllTopsNewUserCase
import com.uce.aplicacion1.logic.usercase.GetOneTopNewUserCase
import com.uce.aplicacion1.ui.activitys.DetailItemActivity
import com.uce.aplicacion1.ui.adapters.NewsAdapter
import com.uce.aplicacion1.ui.adapters.NewsDiffCallback
import com.uce.aplicacion1.ui.entites.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListarNews : Fragment() {

private lateinit var binding: FragmentListarNewsBinding

//Esto viene del Constrain:
private var items : MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter : NewsAdapter

    private lateinit var modalBottomSheetFragment: MenuBottomSheetFragment

    //REVISAR DONE VA ESTO

//    binding.btnInsert.setOnClickListener {
//        addItem()
//    }
//    binding.buttonOpenBottomSheet.setOnClickListener {
//        showMenuBottomSheet()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //bind es ATACHAR
        binding= FragmentListarNewsBinding.bind(inflater.inflate(
            R.layout.fragment_listar_news,container,false))
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hago el llamado a lo qeu traje del Constrain:  initVariables
        initVariables()
        initData()
        initListeners()

        binding.buttonOpenBottomSheet.setOnClickListener {
         showMenuBottomSheet()
         }

    }


    //Viene del Constrain:
    private fun initVariables() {
        newsAdapter = NewsAdapter(
            {descriptionItem(it)},
            {deleteItem(it)},
            {addItem()})

        binding.rvTopNews.adapter = newsAdapter
        binding.rvTopNews.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
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

    //Viene del Constrain:
    private fun initListeners(){
        binding.refreshRV.setOnRefreshListener{
            initData()
            binding.refreshRV.isRefreshing = false
        }
        binding.btnInsert.setOnClickListener{
            addItem()
        }

        binding.buttonOpenBottomSheet.setOnClickListener {
        showMenuBottomSheet()
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
                    Snackbar.make(binding.refreshRV,it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun descriptionItem(news: NewsDataUI){

       // Snackbar.make(binding.refreshRV, news.name, Snackbar.LENGTH_LONG).show()
        val intent = Intent(
            requireContext(),
            DetailItemActivity::class.java
        ).apply {
            putExtra("id", news.id)
        }
        startActivity(intent)


    }
    private fun deleteItem(position: Int){


        //Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
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
       // menuBottomSheetFragment.show(supportFragmentManager, menuBottomSheetFragment.tag)
    }
    
}