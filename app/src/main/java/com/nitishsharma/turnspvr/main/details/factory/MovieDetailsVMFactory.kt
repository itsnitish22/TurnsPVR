package com.nitishsharma.turnspvr.main.details.factory

import com.nitishsharma.turnspvr.main.details.MovieDetailsViewmodel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MovieDetailsVMFactory {
    fun create(movieId: Int): MovieDetailsViewmodel
}