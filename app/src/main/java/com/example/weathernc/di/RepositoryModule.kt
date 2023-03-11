package com.example.weathernc.di

import com.example.weathernc.data.repository.WeatherDayRepositoryImpl
import com.example.weathernc.data.repository.WeatherHourRepositoryImpl
import com.example.weathernc.domain.repository.WeatherDayRepository
import com.example.weathernc.domain.repository.WeatherHourRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun getWeatherDayRepository(weatherDayRepositoryImpl: WeatherDayRepositoryImpl): WeatherDayRepository

    @Binds
    fun getWeatherHourRepository(weatherHourRepositoryImpl: WeatherHourRepositoryImpl): WeatherHourRepository
}
