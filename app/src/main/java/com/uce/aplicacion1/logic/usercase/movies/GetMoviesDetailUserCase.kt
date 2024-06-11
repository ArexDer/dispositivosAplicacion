package com.uce.aplicacion1.logic.usercase.movies

import com.uce.aplicacion1.data.network.endpoints.NewsEndpoint
import com.uce.aplicacion1.data.network.endpoints.movies.MoviesEndpoint
import com.uce.aplicacion1.data.network.repository.RetrofitBase
import com.uce.aplicacion1.ui.entites.MoviesDataUI
import com.uce.aplicacion1.ui.entites.NewsDataUI
import com.uce.aplicacion1.ui.entites.core.toMoviesDataUI
import com.uce.aplicacion1.ui.entites.core.toNewsDataUI

class GetMoviesDetailUserCase {

    suspend operator fun invoke(): Result<MoviesDataUI> {
        var response = RetrofitBase.returnBaseRetrofitMovies()
            .create(MoviesEndpoint::class.java)
            .getDetailsMovies(12)

        var item : MoviesDataUI = MoviesDataUI(
            1,
            "ON o OF m",
            "Ni idea qeu sea aqui",
            "SIN TITULO",

        )
        return if (response.isSuccessful){

            response.body()?.let { movieDetail ->
                val movieDataUI = MoviesDataUI(
                    id = movieDetail.id,
                    status = movieDetail.status,
                    tagline = movieDetail.tagline,
                    title = movieDetail.title
                )
                Result.success(movieDataUI)
            } ?: Result.failure(Exception("Error: Respuesta vacía"))

        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}
