package com.dbdroid.weathernow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.dbdroid.weathernow.data.repository.WeatherRepository
import com.dbdroid.weathernow.models.GeoWeatherModel
import com.dbdroid.weathernow.models.WeatherData.WeatherData

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather(coroutineExceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            weatherRepository.getWeather()
        }
    }

    fun getGeoWeather(coroutineExceptionHandler: CoroutineExceptionHandler, geoWeatherModel: GeoWeatherModel) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            weatherRepository.getGeoWeather(geoWeatherModel)
        }
    }

    fun updateWeatherData(weatherData: WeatherData) {
        weatherRepository.updateWeatherData(weatherData)
    }

    val weatherLiveData : LiveData<WeatherData?>
        get() = weatherRepository.weatherData

}