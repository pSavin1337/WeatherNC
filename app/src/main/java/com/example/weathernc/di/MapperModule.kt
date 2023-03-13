package com.example.weathernc.di

import com.example.weathernc.data.mappers.LocalMapper
import com.example.weathernc.data.mappers.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {

    @Provides
    fun provideLocalMapper(): LocalMapper = LocalMapper()

    @Provides
    fun provideNetworkMapper(): NetworkMapper = NetworkMapper()

}