package com.example.weathernc.presentation.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.entity.WeatherHourModel
import com.example.weathernc.domain.usecases.GetWeatherDataDayUseCase
import com.example.weathernc.domain.usecases.GetWeatherDataHourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val weatherDataDayUseCase: GetWeatherDataDayUseCase,
    private val weatherDataHourUseCase: GetWeatherDataHourUseCase
) : ViewModel() {

    private val _dayForecastLiveData = MutableLiveData<List<WeatherDayModel>>()
    val dayForecastLiveData = _dayForecastLiveData as LiveData<List<WeatherDayModel>>

    private val _hourForecastLiveData = MutableLiveData<List<WeatherHourModel>>()
    val hourForecastLiveData = _hourForecastLiveData as LiveData<List<WeatherHourModel>>

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData = _errorLiveData as LiveData<String>

    val currentDayForecast = MutableLiveData<WeatherDayModel>()

    var cityName: String = ""

    private fun isNetworkActive(context: Context): Boolean {
        val connectivityManager = ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        ) as ConnectivityManager

        return connectivityManager.activeNetwork != null
    }

    fun getDayForecast(context: Context) {
        viewModelScope.launch {
            when (val dayForecastResult =
                weatherDataDayUseCase.execute(cityName, isNetworkActive(context))) {
                is Result.Success<*> -> {
                    try {
                        _dayForecastLiveData.postValue(dayForecastResult.body as List<WeatherDayModel>)
                    } catch (e: ClassCastException) {
                        _errorLiveData.postValue(e.message)
                    }
                }
                is Result.Error -> _errorLiveData.postValue(dayForecastResult.message)
            }
        }
    }

    fun getHourForecast(city: String, date: String) {
        viewModelScope.launch {
            when (val hourForecastResult = weatherDataHourUseCase.execute(city, date)) {
                is Result.Success<*> -> {
                    try {
                        _hourForecastLiveData.postValue(hourForecastResult.body as List<WeatherHourModel>)
                    } catch (e: ClassCastException) {
                        _errorLiveData.postValue(e.message)
                    }
                }
                is Result.Error -> _errorLiveData.postValue(hourForecastResult.message)
            }
        }
    }

}