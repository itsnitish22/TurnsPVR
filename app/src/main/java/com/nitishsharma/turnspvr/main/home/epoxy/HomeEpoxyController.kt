package com.nitishsharma.turnspvr.main.home.epoxy

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeDataModel
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.turnspvr.main.home.callbacks.HomeItemClickCallback

class HomeEpoxyController(private val itemClickCallback: HomeItemClickCallback) :
    TypedEpoxyController<HomeDataModel>() {
    override fun buildModels(data: HomeDataModel?) {
        data?.let { homeMainData ->
            homeMainData.nowPlayingMovies?.let {
                buildTitle("Now Playing")
                buildMovieList(it, "now-playing")
            }
            homeMainData.popularMovies?.let {
                buildTitle("Trending Now")
                buildMovieList(it, "popular")
            }
            homeMainData.topRatedMovies?.let {
                buildTitle("Top Rated")
                buildMovieList(it, "top-rated")
            }
            homeMainData.upcomingMoves?.let {
                buildTitle("Upcoming")
                buildMovieList(it, "upcoming")
            }
        }
    }

    fun buildTitle(title: String) {
        epoxyHomeTitle {
            id("$title-movie-title")
            category(title)
        }
    }

    fun buildMovieList(movieList: HomeMoviesModel, type: String) {
        val popularMoviesModel = ArrayList<EpoxyHomeMovieModel>()
        movieList.movieList.forEachIndexed { index, result ->
            popularMoviesModel.add(
                EpoxyHomeMovieModel_()
                    .id("movie-$type-$index")
                    .imageUrl(result?.posterPath ?: "")
            )
        }
        carousel {
            id("corousel-$type")
            models(popularMoviesModel)
            padding(Carousel.Padding.dp(16, 0, 16, 0, 16))
        }
    }

    private fun buildPopularMovies(popularMovies: HomeMoviesModel, s: String) {
        epoxyHomeTitle {
            id("popular-movie-title")
            category("Trending Now")
        }
        val popularMoviesModel = ArrayList<EpoxyHomeMovieModel>()
        popularMovies.movieList.forEachIndexed { index, result ->
            popularMoviesModel.add(
                EpoxyHomeMovieModel_()
                    .id("movie-popular-$index")
                    .imageUrl(result?.posterPath ?: "")
            )
        }
        carousel {
            id("Horizontal Course Carousel")
            models(popularMoviesModel)
            padding(Carousel.Padding.dp(16, 0, 16, 0, 16))
        }
    }
}