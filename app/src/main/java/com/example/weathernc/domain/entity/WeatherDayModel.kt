package com.example.weathernc.domain.entity

import androidx.room.Entity
import com.example.weathernc.Constants.WEATHER_DAY_TABLE_NAME

@Entity(
    primaryKeys = ["city", "date"],
    tableName = WEATHER_DAY_TABLE_NAME
)
data class WeatherDayModel (
    val date: String,
    val minimalTemperature: Int,
    val averageTemperature: Int,
    val city: String,
    val maximumTemperature: Int,
    val humidity: Int,
    val windSpeed: Double,
    val iconUrl: String
)