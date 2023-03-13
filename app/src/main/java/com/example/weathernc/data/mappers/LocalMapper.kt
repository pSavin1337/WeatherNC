package com.example.weathernc.data.mappers

import com.example.weathernc.data.database.models.WeatherDayDBModel
import com.example.weathernc.data.database.models.WeatherHourDBModel
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.entity.WeatherHourModel

class LocalMapper {

    fun toWeatherDayDBModel(weatherDayModel: WeatherDayModel) =
        with(weatherDayModel) {
            WeatherDayDBModel(
                date = date,
                minimalTemperature = minimalTemperature,
                averageTemperature = averageTemperature,
                maximumTemperature = maximumTemperature,
                city = city.lowercase(),
                humidity = humidity,
                windSpeed = windSpeed,
                iconUrl = iconUrl
            )
        }

    fun toWeatherHourDBModel(weatherHourModel: WeatherHourModel) =
        with(weatherHourModel) {
            WeatherHourDBModel(
                city = city.lowercase(),
                date = date,
                hour = hour,
                temperature = temperature,
                iconUrl = iconUrl
            )
        }

    fun toWeatherDayModel(weatherDayDBModel: WeatherDayDBModel) =
        with(weatherDayDBModel) {
            WeatherDayModel(
                date = date,
                minimalTemperature = minimalTemperature,
                averageTemperature = averageTemperature,
                maximumTemperature = maximumTemperature,
                city = city,
                humidity = humidity,
                windSpeed = windSpeed,
                iconUrl = iconUrl
            )
        }

    fun toWeatherHourModel(weatherHourDBModel: WeatherHourDBModel) =
        with(weatherHourDBModel) {
            WeatherHourModel(
                city = city,
                date = date,
                hour = hour,
                temperature = temperature,
                iconUrl = iconUrl
            )
        }

}