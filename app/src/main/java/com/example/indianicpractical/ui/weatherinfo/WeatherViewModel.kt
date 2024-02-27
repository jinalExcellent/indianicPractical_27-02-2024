package com.example.indianicpractical.ui.weatherinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indianicpractical.network.WeatherApiService
import com.example.indianicpractical.utils.ApiConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherApiClient: WeatherApiService) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _forecastData = MutableLiveData<FiveDaysForecastResponse>()
    val forecastData: LiveData<FiveDaysForecastResponse> get() = _forecastData

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    weatherApiClient.getWeather(cityName, ApiConstant.API_KEY)
                }
                _weatherData.value = response
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

    fun getForecastInfo(cityName: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    weatherApiClient.getForeCast(cityName, ApiConstant.API_KEY)
                }
                _forecastData.value = response
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }

    }
}