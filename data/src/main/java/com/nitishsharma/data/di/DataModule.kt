package com.nitishsharma.data.di

import com.nitishsharma.data.api.apiservice.TurnsApiRepositoryImpl
import com.nitishsharma.domain.repository.TurnsApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTurnsApiRepository(apiRepositoryImpl: TurnsApiRepositoryImpl): TurnsApiRepository {
        return apiRepositoryImpl
    }
}
