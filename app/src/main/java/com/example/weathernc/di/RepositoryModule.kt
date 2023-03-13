package com.example.weathernc.di

import com.example.weathernc.data.repository.LocalRepositoryImpl
import com.example.weathernc.data.repository.NetworkRepositoryImpl
import com.example.weathernc.domain.repository.LocalRepository
import com.example.weathernc.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun getLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

    @Binds
    fun getNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}
