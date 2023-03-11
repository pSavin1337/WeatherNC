package com.example.weathernc.data.database.models

data class WeatherModel(
    val weatherDayModel: List<WeatherDayDBModel>,
    val weatherHourModel: List<WeatherHourDBModel>
)
