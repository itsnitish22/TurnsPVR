package com.nitishsharma.turnspvr.application.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nitishsharma.turnspvr.application.common.SwipeRefreshCallback
import timber.log.Timber

fun RecyclerView.scrollTopTop() {
    this.scrollToPosition(0)
}

fun SwipeRefreshLayout.setupSwipeRefresh(callback: SwipeRefreshCallback) {
    this.setOnRefreshListener {
        this.isRefreshing = true
        callback.onForcedRefresh()
    }
}

fun Fragment.navigate(navDirections: NavDirections) {
    try {
        findNavController().navigate(navDirections)
    } catch (exc: Exception) {
        exc.message?.let { Timber.e(it) }
    }
}