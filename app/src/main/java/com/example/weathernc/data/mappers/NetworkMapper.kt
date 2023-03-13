package com.example.weathernc.data.mappers

import com.example.weathernc.data.network.models.WeatherApiResponseModel
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.entity.WeatherHourModel
import com.example.weathernc.domain.entity.WeatherModel

class NetworkMapper {

    fun toWeatherDayModel(weatherApiResponseModel: WeatherApiResponseModel): WeatherModel {
        val weatherDayModelList = ArrayList<WeatherDayModel>()
        val weatherHourModelList = ArrayList<WeatherHourModel>()
        weatherApiResponseModel.forecast.forecastday.forEach { dayForecast ->
            weatherDayModelList.add(
                WeatherDayModel(
                    date = dayForecast.date,
                    city = weatherApiResponseModel.location.name,
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
                    WeatherHourModel(
                        city = weatherApiResponseModel.location.name,
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

}