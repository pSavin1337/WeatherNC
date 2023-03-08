package com.example.weathernc.di

import com.example.weathernc.data.WeatherMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideMapper(): WeatherMapper = WeatherMapper()

}