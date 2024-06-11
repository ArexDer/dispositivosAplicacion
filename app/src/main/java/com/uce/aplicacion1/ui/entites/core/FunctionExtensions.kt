package com.uce.aplicacion1.ui.entites.core

import com.uce.aplicacion1.data.network.entities.movies.moviesDetail
import com.uce.aplicacion1.data.network.entities.topNews.Data
import com.uce.aplicacion1.ui.entites.MoviesDataUI
import com.uce.aplicacion1.ui.entites.NewsDataUI

class FunctionExtensions

    fun Data.toNewsDataUI() = NewsDataUI(
        this.uuid,
        this.url,
        this.title,
        this.image_url,
        this.description,
        this.language
    )

fun moviesDetail.toMoviesDataUI()= MoviesDataUI(
    this.id,
    this.homepage,
    this.title,
    this.status,

)

