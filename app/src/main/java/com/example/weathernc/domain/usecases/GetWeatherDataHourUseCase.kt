package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.repository.WeatherHourRepository
import javax.inject.Inject

class GetWeatherDataHourUseCase @Inject constructor(
    private val weatherHourRepository: WeatherHourRepository
) : UseCase {

    override suspend fun execute() = weatherHourRepository.getWeatherHourData()

}