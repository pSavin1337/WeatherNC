package com.example.weathernc.data.repository

import com.example.weathernc.data.WeatherMapper
import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherHourRepository
import javax.inject.Inject

class WeatherHourRepositoryImpl @Inject constructor(
    private val weatherHourDao: WeatherHourDao,
    private val mapper: WeatherMapper
) : WeatherHourRepository {

    override suspend fun getWeatherHourData(city: String, date: String): Result =
        Result.Success(
            weatherHourDao.getWeatherHourDataByCity(city, date)
                .map { mapper.toWeatherHourModel(it) }
        )

}