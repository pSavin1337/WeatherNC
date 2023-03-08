package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.WeatherDayRepository
import javax.inject.Inject

class GetWeatherDataDayUseCase @Inject constructor(
    private val weatherDayRepository: WeatherDayRepository,
    private val city: String
) : UseCase {

    override suspend fun execute(): Result = weatherDayRepository.getWeatherDayData(city)

}