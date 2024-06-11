package com.uce.aplicacion1.ui.entites

import com.uce.aplicacion1.data.network.entities.topNews.Data
import org.intellij.lang.annotations.Language

data class NewsDataUI (
    val id: String,
    val url: String,
    val name: String,
    val image: String,
    val description: String,
    val language: String
)



