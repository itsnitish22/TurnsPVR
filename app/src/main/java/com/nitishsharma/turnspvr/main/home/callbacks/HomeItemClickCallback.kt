package com.nitishsharma.turnspvr.main.home.callbacks

import com.nitishsharma.turnspvr.main.home.HomeFragmentDirections
import com.nitishsharma.turnspvr.main.home.HomeViewModel

class HomeItemClickCallback(private val viewModel: HomeViewModel) {
    fun onMovieClick(movieId: Int) {
        viewModel.updateNavDirection(
            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                movieId
            )
        )
    }
}