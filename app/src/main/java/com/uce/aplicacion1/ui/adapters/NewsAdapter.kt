package com.uce.aplicacion1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.uce.aplicacion1.R
import com.uce.aplicacion1.databinding.ItemTopNewsBinding
import com.uce.aplicacion1.ui.entites.NewsDataUI

class NewsAdapter(
    private val onClickItem: (NewsDataUI) -> Unit,
    private val onDeleteItem: (Int) -> Unit,
    private val onInsertItem: () -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var listItems: List<NewsDataUI> = emptyList()

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTopNewsBinding.bind(view)

        fun render(
            data: NewsDataUI,
            onClickItem: (NewsDataUI) -> Unit,
            onDeleteItem: (Int) -> Unit,
            onInsertItem: () -> Unit
        ) {
            binding.txtTitleNews.text = data.name
            binding.txtUrlNews.text = data.url
            binding.txtDescNews.text = data.description
            binding.imgNews.load(data.image) {
                placeholder(R.drawable.clarin)
            }
            itemView.setOnClickListener {
                onClickItem(data)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteItem(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            inflater.inflate(
                R.layout.item_top_news,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.render(
            listItems[position],
            onClickItem,
            onDeleteItem,
            onInsertItem
        )
    }
}



//package com.uce.aplicacion1.ui.adapters
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.uce.aplicacion1.R
//import com.uce.aplicacion1.data.network.entities.topNews.Data
//import com.uce.aplicacion1.databinding.ItemTopNewsBinding
//import com.uce.aplicacion1.ui.entites.NewsDataUI
//
//class NewsAdapter(
//
//    private val onClickItem: (NewsDataUI) -> Unit,
//    private val onDeleteItem: (Int) ->Unit,
//    private val onInsertItem: () -> Unit
//) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
//     var listItems: List<NewsDataUI> = emptyList()
//    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){
//
//        private val binding = ItemTopNewsBinding.bind(view)
//
//        //ingresa elemento por elemento
//        fun render(
//            data: NewsDataUI,
//            onClickItem: (NewsDataUI) -> Unit,
//            onDeleteItem: (Int) -> Unit,
//            onInsertItem: () -> Unit
//        ){
//            binding.txtTitleNews.text = data.name
//            binding.txtUrlNews.text = data.url
//            binding.txtDescNews.text = data.description
//            binding.imgNews
//                .load(data.image) {
//                    placeholder(R.drawable.clarin)
//                }
//            itemView.setOnClickListener{
//                onClickItem(data)
//            }
//            binding.btnDelete.setOnClickListener {
//                //nos da la poscion en la cual damos clic o tocamos :V
//                onDeleteItem(adapterPosition)
//            }
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        // va a estar alojado en un activity
//        val inflater = LayoutInflater.from(parent.context)   //layoutInflater -> es la que nos permitia levantar cualquier cosa de la vista como objeto
//        return  NewsViewHolder(
//            inflater.inflate(
//                R.layout.item_top_news,
//                parent,
//                false
//            )
//        )
//    }
//
//
//    override fun getItemCount(): Int { // cuenta los items que van entrando y me decuelve en valor
//
//        return listItems.size
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) { //funcion que se repite cada vez
//
//        holder.render(
//            listItems[position],
//            onClickItem,
//            onDeleteItem,
//            onInsertItem
//        )
//    }
//
//}