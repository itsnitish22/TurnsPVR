package com.nitishsharma.domain.models.TurnsPVRModels.home

data class HomeDataModel(
    var nowPlayingMovies: HomeMoviesModel? = null,
    var popularMovies: HomeMoviesModel? = null,
    var topRatedMovies: HomeMoviesModel? = null,
    var upcomingMoves: HomeMoviesModel? = null
)