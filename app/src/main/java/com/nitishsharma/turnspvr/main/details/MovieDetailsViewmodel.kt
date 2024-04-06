package com.nitishsharma.turnspvr.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.interactors.usecases.MovieDetailsUseCase
import com.nitishsharma.domain.models.TurnsPVRModels.details.MovieDetailsModel
import com.nitishsharma.turnspvr.application.common.CommonViewModel
import com.nitishsharma.turnspvr.application.common.LoadingModel
import com.nitishsharma.turnspvr.application.common.LoadingState
import com.nitishsharma.turnspvr.main.details.factory.MovieDetailsVMFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewmodel @AssistedInject constructor(
    @Assisted private val movieId: Int,
    private val movieDetailsUseCase: MovieDetailsUseCase
) : CommonViewModel() {
    private val _movieDetails: MutableLiveData<MovieDetailsModel> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailsModel> get() = _movieDetails

    init {
        getTopLevelMovieDetails()
    }

    fun getTopLevelMovieDetails() {
        viewModelScope.launch {
            updateLoadingModel(LoadingModel(LoadingState.LOADING, null, isListEmpty()))
            val response = movieDetailsUseCase(movieId)
            if (response is Result.Success) {
                _movieDetails.postValue(response.data)
                updateLoadingModel(LoadingModel(LoadingState.COMPLETED, null, isListEmpty()))
            } else if (response is Result.Error) {
                updateLoadingModel(
                    LoadingModel(
                        LoadingState.ERROR,
                        response.exception,
                        isListEmpty()
                    )
                )
                Timber.e("Error fetching movie details: ${response.exception}")
            }
        }
    }

    private fun isListEmpty() = _movieDetails.value == null

    companion object {
        fun provideFactory(
            factory: MovieDetailsVMFactory,
            movieId: Int,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(movieId) as T
            }
        }
    }
}