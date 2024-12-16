package com.dbdroid.weathernow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbdroid.weathernow.data.api.WeatherService
import com.dbdroid.weathernow.models.GeoWeatherModel
import com.dbdroid.weathernow.models.WeatherData.WeatherData

class WeatherRepository(private val weatherService: WeatherService) {

    private val weatherLiveData = MutableLiveData<WeatherData?>()

    val weatherData: LiveData<WeatherData?>
        get() = weatherLiveData

    suspend fun getWeather() {
        val weatherResult = weatherService.getWeather()

        if(weatherResult.isSuccessful && weatherResult.body() != null) {
            weatherLiveData.postValue(weatherResult.body())
        } else {
            weatherLiveData.postValue(null)
        }
    }

    suspend fun getGeoWeather(geoWeatherModel: GeoWeatherModel) {
        val weatherResult = weatherService.getGeoWeather(geoWeatherModel)

        if(weatherResult.isSuccessful && weatherResult.body() != null) {
            weatherLiveData.postValue(weatherResult.body())
        } else {
            weatherLiveData.postValue(null)
        }
    }

    fun updateWeatherData(weatherData: WeatherData) {
        weatherLiveData.postValue(weatherData)
    }

}