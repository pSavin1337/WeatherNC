package com.example.weathernc.data.repository

import com.example.weathernc.Constants
import com.example.weathernc.data.mappers.NetworkMapper
import com.example.weathernc.data.network.WeatherApi
import com.example.weathernc.data.network.models.ErrorModel
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.repository.NetworkRepository
import com.google.gson.Gson
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val mapper: NetworkMapper,
    private val gson: Gson
) : NetworkRepository {

    override suspend fun getWeatherData(city: String): Result {
        return try {
            val response = weatherApi.getWeatherDataByCity(city)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    val weatherModel = mapper.toWeatherDayModel(responseBody)
                    Result.Success(weatherModel)
                } else {
                    Result.Error(Constants.UNKNOWN_ERROR)
                }
            } else {
                val errorModel =
                    gson.fromJson(response.errorBody()?.string(), ErrorModel::class.java)
                Result.Error(errorModel.error.message)
            }
        } catch (e: java.lang.Exception) {
            Result.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

}