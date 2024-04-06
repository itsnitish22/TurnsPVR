package com.nitishsharma.turnspvr.application.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nitishsharma.turnspvr.application.common.SwipeRefreshCallback

fun RecyclerView.scrollTopTop() {
    this.scrollToPosition(0)
}

fun SwipeRefreshLayout.setupSwipeRefresh(callback: SwipeRefreshCallback) {
    this.setOnRefreshListener {
        this.isRefreshing = true
        callback.onForcedRefresh()
    }
}