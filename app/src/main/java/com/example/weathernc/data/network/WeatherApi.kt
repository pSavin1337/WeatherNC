package com.example.weathernc.data.network

import com.example.weathernc.Constants.API_KEY
import com.example.weathernc.Constants.FORECAST_DAYS_COUNT
import com.example.weathernc.data.network.models.WeatherApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("forecast.json")
    suspend fun getWeatherDataByCity(
        @Query("q") city: String,
        @Query("days") days: Int = FORECAST_DAYS_COUNT
    ): Response<WeatherApiResponseModel>

}