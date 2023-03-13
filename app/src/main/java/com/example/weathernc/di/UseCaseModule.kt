package com.example.weathernc.di

import com.example.weathernc.domain.repository.LocalRepository
import com.example.weathernc.domain.repository.NetworkRepository
import com.example.weathernc.domain.usecases.GetWeatherDataDayUseCase
import com.example.weathernc.domain.usecases.GetWeatherDataHourUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideWeatherDayUseCase(
        localRepository: LocalRepository,
        networkRepository: NetworkRepository
    ) = GetWeatherDataDayUseCase(localRepository, networkRepository)

    @Provides
    fun provideWeatherHourUseCase(localRepository: LocalRepository) =
        GetWeatherDataHourUseCase(localRepository)

}