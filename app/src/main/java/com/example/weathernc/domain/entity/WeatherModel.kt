package com.example.weathernc.domain.entity

data class WeatherModel(
    val weatherDayModel: List<WeatherDayModel>,
    val weatherHourModel: List<WeatherHourModel>
)
