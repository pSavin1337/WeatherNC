package com.example.weathernc.presentation.detailforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherHourModel
import com.example.weathernc.domain.usecases.GetWeatherDataHourUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailForecastViewModel @Inject constructor(
    private val weatherDataHourUseCase: GetWeatherDataHourUseCase
): ViewModel() {

    private val _hourForecastLiveData = MutableLiveData<List<WeatherHourModel>>()
    val hourForecastLiveData = _hourForecastLiveData as LiveData<List<WeatherHourModel>>

    private val _errorLiveData = MutableLiveData(false)
    val errorLiveData = _errorLiveData as LiveData<Boolean>

    fun onViewCreated(city: String, date: String) {
        getHourForecast(city, date)
    }

    private fun getHourForecast(city: String, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val hourForecastResult = weatherDataHourUseCase.execute(city, date)) {
                is Result.Success<*> -> {
                    try {
                        _hourForecastLiveData.postValue(hourForecastResult.body as List<WeatherHourModel>)
                    } catch (e: ClassCastException) {
                        _errorLiveData.postValue(true)
                    }
                }
                is Result.Error -> _errorLiveData.postValue(true)
            }
        }
    }

}