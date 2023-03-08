package com.example.weathernc.domain.repository

import com.example.weathernc.domain.entity.Result

interface WeatherHourRepository {

    suspend fun getWeatherHourData(): Result

}