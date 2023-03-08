package com.example.weathernc.domain.repository

import com.example.weathernc.domain.entity.Result

interface WeatherDayRepository {

    suspend fun getWeatherDayData(city: String): Result

}