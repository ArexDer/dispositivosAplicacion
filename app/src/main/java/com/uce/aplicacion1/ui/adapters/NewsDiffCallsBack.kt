package com.uce.aplicacion1.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.uce.aplicacion1.ui.entites.NewsDataUI

class NewsDiffCallback(
    private val oldList: MutableList<NewsDataUI>,
    private val newList: MutableList<NewsDataUI>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
