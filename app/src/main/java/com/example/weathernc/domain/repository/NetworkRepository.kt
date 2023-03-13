package com.example.weathernc.domain.repository

import com.example.weathernc.domain.entity.Result

interface NetworkRepository {

    suspend fun getWeatherData(city: String): Result

}