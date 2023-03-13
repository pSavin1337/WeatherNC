package com.example.weathernc.data.repository

import com.example.weathernc.Constants
import com.example.weathernc.data.mappers.LocalMapper
import com.example.weathernc.data.database.dao.WeatherDayDao
import com.example.weathernc.data.database.dao.WeatherHourDao
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherModel
import com.example.weathernc.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val weatherHourDao: WeatherHourDao,
    private val weatherDayDao: WeatherDayDao,
    private val mapper: LocalMapper
) : LocalRepository {

    override suspend fun getWeatherDayData(city: String): Result {
        return try {
            val result = weatherDayDao.getWeatherDayDataByCity(city.lowercase())
                .map { mapper.toWeatherDayModel(it) }
            if (result.isNotEmpty())
                Result.Success(result)
            else
                Result.Error(Constants.NOT_FOUND_DATA_ERROR)
        } catch (e: java.lang.Exception) {
            Result.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun getWeatherHourData(city: String, date: String): Result {
        return try {
            val result = weatherHourDao.getWeatherHourDataByCity(city.lowercase(), date)
                .map { mapper.toWeatherHourModel(it) }
            if (result.isNotEmpty())
                Result.Success(result)
            else
                Result.Error(Constants.NOT_FOUND_DATA_ERROR)
        } catch (e: java.lang.Exception) {
            Result.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun setWeatherData(weatherModel: WeatherModel) {
        val cityName = weatherModel.weatherDayModel[0].city.lowercase()
        weatherDayDao.deleteWeatherDayDataByCity(cityName)
        val weatherDayDbModel = weatherModel.weatherDayModel.map { mapper.toWeatherDayDBModel(it) }
        val weatherHourDbModel =
            weatherModel.weatherHourModel.map { mapper.toWeatherHourDBModel(it) }
        weatherDayDao.insertWeatherDayData(weatherDayDbModel)
        weatherHourDao.insertWeatherHourData(weatherHourDbModel)
    }

}