package com.nitishsharma.data.api.apiservice

import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.domain.models.TurnsPVRModels.NewTokenModel
import com.nitishsharma.domain.models.TurnsPVRModels.details.MovieDetailsModel
import com.nitishsharma.domain.repository.TurnsApiRepository
import javax.inject.Inject

class TurnsApiRepositoryImpl @Inject constructor(private val turnsApiService: TurnsPVRApiService) :
    TurnsApiRepository {
    override suspend fun getNewToken(): Result<NewTokenModel> {
        return try {
            val response = turnsApiService.getNewToken()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPopularMovies(movieType: String): Result<HomeMoviesModel> {
        return try {
            val response = turnsApiService.getPopularMovies(movieType)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetailsModel> {
        return try {
            val response = turnsApiService.getMovieDetails(movieId)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}