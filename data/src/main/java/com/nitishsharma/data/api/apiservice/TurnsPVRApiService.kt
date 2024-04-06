package com.nitishsharma.data.api.apiservice

import com.nitishsharma.data.api.apiconstants.Endpoints
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.domain.models.TurnsPVRModels.NewTokenModel
import retrofit2.http.GET
import retrofit2.http.Path

interface TurnsPVRApiService {
    @GET(Endpoints.NEW_TOKEN)
    suspend fun getNewToken(): NewTokenModel

    @GET(Endpoints.POPULAR_MOVIES)
    suspend fun getPopularMovies(@Path("movie_type") movieType: String): HomeMoviesModel

}