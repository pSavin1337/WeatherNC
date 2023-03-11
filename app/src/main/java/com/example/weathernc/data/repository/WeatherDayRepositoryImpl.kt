package com.example.weathernc.data.repository

import com.example.weathernc.data.WeatherMapper
import com.example.weathernc.data.database.dao.WeatherDayDao
import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.data.network.WeatherApi
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherDayRepository
import javax.inject.Inject

class WeatherDayRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDayDao: WeatherDayDao,
    private val weatherHourDao: WeatherHourDao,
    private val mapper: WeatherMapper
) : WeatherDayRepository {

    override suspend fun getWeatherDayData(city: String): Result {
        val response = weatherApi.getWeatherDataByCity(city)
        val responseBody = response.body()
        return if (response.isSuccessful && responseBody != null) {
            val cityName = responseBody.location.name.lowercase()
            val weatherModel = mapper.toWeatherDBModel(responseBody)
            weatherDayDao.deleteWeatherDayDataByCity(cityName)
            weatherDayDao.insertWeatherDayData(weatherModel.weatherDayModel)
            weatherHourDao.insertWeatherHourData(weatherModel.weatherHourModel)
            Result.Success(
                weatherDayDao.getWeatherDayDataByCity(cityName)
                    .map { mapper.toWeatherDayModel(it) }
            )
        } else {
            Result.Error
        }
    }

}