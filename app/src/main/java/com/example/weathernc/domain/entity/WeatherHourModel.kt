package com.example.weathernc.domain.entity

data class WeatherHourModel(
    val city: String,
    val date: String,
    val hour: String,
    val temperature: Int,
    val iconUrl: String
)