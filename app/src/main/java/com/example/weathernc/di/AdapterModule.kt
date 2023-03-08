package com.example.weathernc.di

import com.example.weathernc.presentation.detailforecast.HourForecastAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Provides
    fun provideAdapter(): HourForecastAdapter = HourForecastAdapter()

}