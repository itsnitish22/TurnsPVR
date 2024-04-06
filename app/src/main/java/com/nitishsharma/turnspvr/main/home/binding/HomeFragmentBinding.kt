package com.nitishsharma.turnspvr.main.home.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nitishsharma.turnspvr.R
import com.nitishsharma.turnspvr.application.common.LoadingModel
import com.nitishsharma.turnspvr.application.common.LoadingState

@BindingAdapter("urlToImage")
fun loadImageTopRoundedCorner(imageView: AppCompatImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(imageView.context).load("https://image.tmdb.org/t/p/w500" + imageUrl)
            .skipMemoryCache(true)
            .error(
                Glide.with(imageView.context)
                    .load(ContextCompat.getDrawable(imageView.context, R.drawable.movie_dummy_img))
            )
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)

    } else {
        Glide.with(imageView.context)
            .load(ContextCompat.getDrawable(imageView.context, R.drawable.movie_dummy_img))
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }
}

@BindingAdapter("updateSwipeRefresh")
fun updateSwipeRefresh(view: SwipeRefreshLayout, loadingObj: LoadingModel?) {
    if (loadingObj?.loadingModel != LoadingState.LOADING) {
        view.isRefreshing = false
    }
}