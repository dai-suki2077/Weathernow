package com.dbdroid.weathernow.models.WeatherData

import com.dbdroid.weathernow.models.WeatherData.CurrentWeather.CurrentWeather
import com.dbdroid.weathernow.models.WeatherData.DailyWeather.DailyWeather
import com.dbdroid.weathernow.models.WeatherData.HourlyWeather.HourlyWeather

data class WeatherData(
    val city: String,
    val country: String,
    val lat: Double,
    val long: Double,
    val current_weather: CurrentWeather,
    val daily_weather: DailyWeather,
    val hourly_weather: HourlyWeather
)