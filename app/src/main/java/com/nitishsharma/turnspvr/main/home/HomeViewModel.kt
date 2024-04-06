package com.nitishsharma.turnspvr.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nitishsharma.domain.interactors.result.Result
import com.nitishsharma.domain.interactors.usecases.HomeMoviesUseCase
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeDataModel
import com.nitishsharma.domain.models.TurnsPVRModels.home.HomeMoviesModel
import com.nitishsharma.turnspvr.application.common.CommonViewModel
import com.nitishsharma.turnspvr.application.common.LoadingModel
import com.nitishsharma.turnspvr.application.common.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeMoviesUseCase: HomeMoviesUseCase) :
    CommonViewModel() {
    private var nowPlayingMoviesLocal: HomeMoviesModel? = null
    private var topRatedMoviesLocal: HomeMoviesModel? = null
    private var popularMoviesLocal: HomeMoviesModel? = null
    private var upcomingMoviesLocal: HomeMoviesModel? = null

    private val _homeMainModel: MutableLiveData<HomeDataModel> = MutableLiveData()
    val homeMainModel: LiveData<HomeDataModel> get() = _homeMainModel

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        getNowPlayingMovies()
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            updateLoadingModel(LoadingModel(LoadingState.LOADING, null, isListEmpty()))
            val nowPlayingResponse = homeMoviesUseCase("now_playing")
            if (nowPlayingResponse is Result.Success) {
                nowPlayingMoviesLocal = nowPlayingResponse.data
                addMovies(MovieType.NOW_PLAYING, nowPlayingResponse.data)
                updateLoadingModel(LoadingModel(LoadingState.COMPLETED, null, isListEmpty()))
            } else if (nowPlayingResponse is Result.Error) {
                updateLoadingModel(
                    LoadingModel(
                        LoadingState.ERROR,
                        nowPlayingResponse.exception,
                        isListEmpty()
                    )
                )
                Timber.e("Unable to fetch now playing movies: ${nowPlayingResponse.exception}")
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            updateLoadingModel(LoadingModel(LoadingState.LOADING, null, isListEmpty()))
            val popularMoviesResponse = homeMoviesUseCase("popular")
            if (popularMoviesResponse is Result.Success) {
                popularMoviesLocal = popularMoviesResponse.data
                addMovies(MovieType.POPULAR, popularMoviesResponse.data)
                updateLoadingModel(LoadingModel(LoadingState.COMPLETED, null, isListEmpty()))
            } else if (popularMoviesResponse is Result.Error) {
                updateLoadingModel(
                    LoadingModel(
                        LoadingState.ERROR,
                        popularMoviesResponse.exception,
                        isListEmpty()
                    )
                )
                Timber.e("Unable to fetch popular movies: ${popularMoviesResponse.exception}")
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            updateLoadingModel(LoadingModel(LoadingState.LOADING, null, isListEmpty()))
            val topRatedResponse = homeMoviesUseCase("top_rated")
            if (topRatedResponse is Result.Success) {
                topRatedMoviesLocal = topRatedResponse.data
                addMovies(MovieType.TOP_RATED, topRatedResponse.data)
                updateLoadingModel(LoadingModel(LoadingState.COMPLETED, null, isListEmpty()))
            } else if (topRatedResponse is Result.Error) {
                updateLoadingModel(
                    LoadingModel(
                        LoadingState.ERROR,
                        topRatedResponse.exception,
                        isListEmpty()
                    )
                )
                Timber.e("Unable to fetch top rated movies: ${topRatedResponse.exception}")
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            updateLoadingModel(LoadingModel(LoadingState.LOADING, null, isListEmpty()))
            val upcomingMoviesResponse = homeMoviesUseCase("upcoming")
            if (upcomingMoviesResponse is Result.Success) {
                upcomingMoviesLocal = upcomingMoviesResponse.data
                addMovies(MovieType.UPCOMING, upcomingMoviesResponse.data)
                updateLoadingModel(LoadingModel(LoadingState.COMPLETED, null, isListEmpty()))
            } else if (upcomingMoviesResponse is Result.Error) {
                updateLoadingModel(
                    LoadingModel(
                        LoadingState.ERROR,
                        upcomingMoviesResponse.exception,
                        isListEmpty()
                    )
                )
                Timber.e("Unable to fetch upcoming movies: ${upcomingMoviesResponse.exception}")
            }
        }
    }

    private fun addMovies(movieType: MovieType, newData: HomeMoviesModel) {
        when (movieType) {
            MovieType.NOW_PLAYING -> {
                val previousData = _homeMainModel.value
                previousData?.let {
                    it.nowPlayingMovies = newData
                    _homeMainModel.postValue(it)
                } ?: run {
                    val data = HomeDataModel(
                        nowPlayingMovies = newData,
                        popularMovies = popularMoviesLocal,
                        topRatedMovies = topRatedMoviesLocal,
                        upcomingMoves = upcomingMoviesLocal
                    )
                    _homeMainModel.postValue(data)
                }
            }

            MovieType.POPULAR -> {
                val previousData = _homeMainModel.value
                previousData?.let {
                    it.popularMovies = newData
                    _homeMainModel.postValue(it)
                } ?: run {
                    val data = HomeDataModel(
                        nowPlayingMovies = nowPlayingMoviesLocal,
                        popularMovies = newData,
                        topRatedMovies = topRatedMoviesLocal,
                        upcomingMoves = upcomingMoviesLocal
                    )
                    _homeMainModel.postValue(data)
                }
            }

            MovieType.TOP_RATED -> {
                val previousData = _homeMainModel.value
                previousData?.let {
                    it.topRatedMovies = newData
                    _homeMainModel.postValue(it)
                } ?: run {
                    val data = HomeDataModel(
                        nowPlayingMovies = nowPlayingMoviesLocal,
                        popularMovies = popularMoviesLocal,
                        topRatedMovies = newData,
                        upcomingMoves = upcomingMoviesLocal
                    )
                    _homeMainModel.postValue(data)
                }
            }

            MovieType.UPCOMING -> {
                val previousData = _homeMainModel.value
                previousData?.let {
                    it.upcomingMoves = newData
                    _homeMainModel.postValue(it)
                } ?: run {
                    val data = HomeDataModel(
                        nowPlayingMovies = nowPlayingMoviesLocal,
                        popularMovies = popularMoviesLocal,
                        topRatedMovies = topRatedMoviesLocal,
                        upcomingMoves = newData
                    )
                    _homeMainModel.postValue(data)
                }
            }
        }
    }

    private fun isListEmpty() = _homeMainModel.value == null
}

enum class MovieType {
    POPULAR, TOP_RATED, UPCOMING, NOW_PLAYING
}