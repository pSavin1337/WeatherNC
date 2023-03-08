package com.example.weathernc.data.repository

import com.example.weathernc.data.WeatherMapper
import com.example.weathernc.data.database.dao.WeatherDayDao
import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.data.network.WeatherApi
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherDayRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = WeatherDayRepository::class, component = SingletonComponent::class)
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
            val weatherModel = mapper.toWeatherModel(responseBody)
            weatherDayDao.deleteWeatherDayDataByCity(city)
            weatherDayDao.insertWeatherDayData(weatherModel.weatherDayModel)
            weatherHourDao.insertWeatherHourData(weatherModel.weatherHourModel)
            Result.Success(weatherDayDao.getWeatherDayDataByCity(city))
        } else {
            Result.Error
        }
    }

}