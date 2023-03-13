package com.example.weathernc.domain.usecases

import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherModel
import com.example.weathernc.domain.repository.LocalRepository
import com.example.weathernc.domain.repository.NetworkRepository
import javax.inject.Inject

class GetWeatherDataDayUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val networkRepository: NetworkRepository
) {

    suspend fun execute(city: String, isNetworkActive: Boolean): Result {
        return if (isNetworkActive) {
            when (val networkData = networkRepository.getWeatherData(city)) {
                is Result.Success<*> -> {
                    localRepository.setWeatherData(networkData.body as WeatherModel)
                    localRepository.getWeatherDayData(networkData.body.weatherDayModel[0].city)
                }
                is Result.Error -> networkData
            }
        } else {
            localRepository.getWeatherDayData(city)
        }
    }

}