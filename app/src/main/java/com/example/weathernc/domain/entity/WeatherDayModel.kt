package com.example.weathernc.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDayModel(
    val date: String,
    val minimalTemperature: Int,
    val averageTemperature: Int,
    val city: String,
    val maximumTemperature: Int,
    val humidity: Int,
    val windSpeed: Double,
    val iconUrl: String
) : Parcelable