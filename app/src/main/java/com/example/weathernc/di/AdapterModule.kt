package com.example.weathernc.di

import com.example.weathernc.presentation.detailforecast.HourForecastAdapter
import com.example.weathernc.presentation.generalforecast.DayForecastAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Provides
    fun provideHourAdapter(): HourForecastAdapter = HourForecastAdapter()

    @Provides
    fun provideDayAdapter(): DayForecastAdapter = DayForecastAdapter()

}