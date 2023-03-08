package com.example.weathernc.di

import com.example.weathernc.domain.repository.WeatherDayRepository
import com.example.weathernc.domain.repository.WeatherHourRepository
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
    fun provideWeatherDayUseCase(repository: WeatherDayRepository) =
        GetWeatherDataDayUseCase(repository)

    @Provides
    fun provideWeatherHourUseCase(repository: WeatherHourRepository) =
        GetWeatherDataHourUseCase(repository)

}