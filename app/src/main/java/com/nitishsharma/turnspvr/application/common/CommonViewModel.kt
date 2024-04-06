package com.nitishsharma.turnspvr.application.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class CommonViewModel @Inject constructor(): ViewModel() {
    private val _loadingModel: MutableLiveData<LoadingModel> = MutableLiveData()
    val loadingModel: LiveData<LoadingModel> get() = _loadingModel

    fun updateLoadingModel(loadingModel: LoadingModel) {
        _loadingModel.postValue(loadingModel)
    }
}