package com.example.weathernc.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherApiResponseModel(
    val forecast: Forecast,
    val location: Location
)

data class Location(
    val name: String
)

data class Forecast (
    val forecastday: List<ForecastDay>
)

data class ForecastDay (
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day (
    @SerializedName("maxtemp_c")
    val maxTempC: Double,

    @SerializedName("mintemp_c")
    val minTempC: Double,

    @SerializedName("avgtemp_c")
    val avgTempC: Double,

    @SerializedName("maxwind_kph")
    val maxWindKph: Double,

    @SerializedName("avghumidity")
    val avgHumidity: Double,

    val condition: Condition
)

data class Hour (

    val time: String,

    @SerializedName("temp_c")
    val tempC: Double,

    val condition: Condition,


)

data class Condition (
    val icon: String
)
