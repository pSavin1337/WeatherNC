package com.example.weathernc.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weathernc.Constants.WEATHER_HOUR_TABLE_NAME
import com.example.weathernc.domain.entity.WeatherHourModel

@Dao
interface WeatherHourDao {

    @Insert
    suspend fun insertWeatherHourData(weatherData: List<WeatherHourModel>)

    @Query("SELECT * FROM $WEATHER_HOUR_TABLE_NAME WHERE city = :city AND date = :date")
    suspend fun getWeatherHourDataByCity(city: String, date: String): List<WeatherHourModel>

}