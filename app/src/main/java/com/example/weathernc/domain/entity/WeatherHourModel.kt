package com.example.weathernc.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.weathernc.Constants.WEATHER_HOUR_TABLE_NAME

@Entity(
    tableName = WEATHER_HOUR_TABLE_NAME,
    primaryKeys = ["city", "date", "hour"],
    foreignKeys = [ForeignKey(
        entity = WeatherDayModel::class,
        parentColumns = ["city", "date"],
        childColumns = ["city", "date"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WeatherHourModel(
    val city: String,
    val date: String,
    val hour: String,
    val temperature: Int,
    val iconUrl: String
)