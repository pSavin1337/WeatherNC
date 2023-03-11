package com.example.weathernc.data

import com.example.weathernc.data.database.models.WeatherDayDBModel
import com.example.weathernc.data.database.models.WeatherHourDBModel
import com.example.weathernc.data.network.models.WeatherApiResponseModel
import com.example.weathernc.data.database.models.WeatherModel
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.entity.WeatherHourModel

class WeatherMapper {

    fun toWeatherDBModel(weatherApiResponseModel: WeatherApiResponseModel): WeatherModel {
        val weatherDayModelList = ArrayList<WeatherDayDBModel>()
        val weatherHourModelList = ArrayList<WeatherHourDBModel>()
        weatherApiResponseModel.forecast.forecastday.forEach { dayForecast ->
            weatherDayModelList.add(
                WeatherDayDBModel(
                    date = dayForecast.date,
                    city = weatherApiResponseModel.location.name.lowercase(),
                    minimalTemperature = dayForecast.day.minTempC.toInt(),
                    averageTemperature = dayForecast.day.avgTempC.toInt(),
                    maximumTemperature = dayForecast.day.maxTempC.toInt(),
                    humidity = dayForecast.day.avgHumidity.toInt(),
                    windSpeed = dayForecast.day.maxWindKph,
                    iconUrl = dayForecast.day.condition.icon
                )
            )
            dayForecast.hour.forEach { hourForecast ->
                val (date, hour) = hourForecast.time.split(' ')
                weatherHourModelList.add(
                    WeatherHourDBModel(
                        city = weatherApiResponseModel.location.name.lowercase(),
                        temperature = hourForecast.tempC.toInt(),
                        iconUrl = hourForecast.condition.icon,
                        date = date,
                        hour = hour
                    )
                )
            }
        }
        return WeatherModel(weatherDayModelList, weatherHourModelList)
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