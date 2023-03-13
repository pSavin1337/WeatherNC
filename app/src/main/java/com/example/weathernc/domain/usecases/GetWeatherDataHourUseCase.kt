package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.LocalRepository
import javax.inject.Inject

class GetWeatherDataHourUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend fun execute(city: String, date: String): Result =
        localRepository.getWeatherHourData(city, date)

}