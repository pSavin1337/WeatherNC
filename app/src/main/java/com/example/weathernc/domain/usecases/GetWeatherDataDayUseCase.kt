package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherDayRepository
import javax.inject.Inject

class GetWeatherDataDayUseCase @Inject constructor(
    private val weatherDayRepository: WeatherDayRepository
) {

    suspend fun execute(city: String): Result = weatherDayRepository.getWeatherDayData(city.lowercase())

}