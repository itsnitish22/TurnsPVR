package com.nitishsharma.turnspvr.main.home.epoxy

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.nitishsharma.turnspvr.BR
import com.nitishsharma.turnspvr.R
import com.nitishsharma.turnspvr.main.home.callbacks.HomeItemClickCallback

@EpoxyModelClass
abstract class EpoxyHomeTitleModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var category: String = ""

    override fun getDefaultLayout(): Int {
        return R.layout.home_title
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        binding.setVariable(BR.category, category)
    }
}

@EpoxyModelClass
abstract class EpoxyHomeMovieModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var imageUrl: String = ""

    @EpoxyAttribute
    var movieId: Int = -1

    @EpoxyAttribute
    lateinit var callback: HomeItemClickCallback

    override fun getDefaultLayout(): Int {
        return R.layout.home_movie_item
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        binding.setVariable(BR.imageUrl, imageUrl)
        binding.setVariable(BR.movieId, movieId)
        binding.setVariable(BR.callback, callback)
    }
}
