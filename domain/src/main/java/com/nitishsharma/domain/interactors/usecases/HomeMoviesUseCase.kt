package com.nitishsharma.domain.interactors.usecases

import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.domain.repository.TurnsApiRepository
import javax.inject.Inject

class HomeMoviesUseCase @Inject constructor(private val apiRepository: TurnsApiRepository) {
    suspend operator fun invoke(movieType: String): Result<HomeMoviesModel> {
            return apiRepository.getPopularMovies(movieType)
    }
}