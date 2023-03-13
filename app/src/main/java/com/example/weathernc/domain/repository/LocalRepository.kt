package com.example.weathernc.domain.repository

import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherModel

interface LocalRepository {

    suspend fun getWeatherDayData(city: String): Result
    suspend fun getWeatherHourData(city: String, date: String): Result
    suspend fun setWeatherData(weatherModel: WeatherModel)

}