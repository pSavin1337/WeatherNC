package com.example.weathernc.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weathernc.Constants.WEATHER_DAY_TABLE_NAME
import com.example.weathernc.domain.entity.WeatherDayModel

@Dao
interface WeatherDayDao {

    @Insert
    suspend fun insertWeatherDayData(weatherData: List<WeatherDayModel>)

    @Query("SELECT * FROM $WEATHER_DAY_TABLE_NAME WHERE city= :city")
    suspend fun getWeatherDayDataByCity(city: String): List<WeatherDayModel>

    @Query("DELETE FROM $WEATHER_DAY_TABLE_NAME WHERE city=:city")
    suspend fun deleteWeatherDayDataByCity(city: String)

}