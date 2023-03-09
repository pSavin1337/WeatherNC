package com.example.weathernc.presentation.generalforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernc.domain.entity.Result
import com.example.weathernc.domain.entity.WeatherDayModel
import com.example.weathernc.domain.usecases.GetWeatherDataDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GeneralForecastViewModel @Inject constructor(
    private val weatherDataDayUseCase: GetWeatherDataDayUseCase
) : ViewModel() {

    private val _dayForecastLiveData = MutableLiveData<List<WeatherDayModel>>()
    val dayForecastLiveData = _dayForecastLiveData as LiveData<List<WeatherDayModel>>

    private val _errorLiveData = MutableLiveData(false)
    val errorLiveData = _errorLiveData as LiveData<Boolean>

    var cityName: String = ""

    fun onViewCreated() {
        getDayForecast()
    }

    fun onSearchButtonClick() {
        getDayForecast()
    }

    private fun getDayForecast() {
        _errorLiveData.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val dayForecastResult = weatherDataDayUseCase.execute(cityName)
            withContext(Dispatchers.Main) {
                when (dayForecastResult) {
                    is Result.Success<*> -> {
                        try {
                            _dayForecastLiveData.postValue(dayForecastResult.body as List<WeatherDayModel>)
                        } catch (e: ClassCastException) {
                            _errorLiveData.value = true
                        }
                    }
                    is Result.Error -> _errorLiveData.value = true
                }
            }
        }
    }

}