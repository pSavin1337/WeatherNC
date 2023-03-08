package com.example.weathernc.data.repository

import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherHourRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = WeatherHourRepository::class, component = SingletonComponent::class)
class WeatherHourRepositoryImpl @Inject constructor(
    private val weatherHourDao: WeatherHourDao,
    private val city: String,
    private val date: String
) : WeatherHourRepository {

    override suspend fun getWeatherHourData(): Result =
        Result.Success(weatherHourDao.getWeatherHourDataByCity(city, date))

}