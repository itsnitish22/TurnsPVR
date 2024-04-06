package com.nitishsharma.domain.interactors.usecases

import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.models.TurnsPVRModels.details.MovieDetailsModel
import com.nitishsharma.domain.repository.TurnsApiRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val apiRepository: TurnsApiRepository) {
    suspend operator fun invoke(movieId: Int): Result<MovieDetailsModel> {
        return apiRepository.getMovieDetails(movieId)
    }
}