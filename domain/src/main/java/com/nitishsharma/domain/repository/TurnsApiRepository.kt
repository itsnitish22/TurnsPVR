package com.nitishsharma.domain.repository

import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.domain.models.TurnsPVRModels.NewTokenModel

interface TurnsApiRepository {
    suspend fun getNewToken(): Result<NewTokenModel>
    suspend fun getPopularMovies(movieType: String): Result<HomeMoviesModel>
}