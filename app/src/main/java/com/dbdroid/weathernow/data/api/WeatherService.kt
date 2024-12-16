package com.dbdroid.weathernow.data.api

import com.dbdroid.weathernow.models.GeoWeatherModel
import com.dbdroid.weathernow.models.WeatherData.WeatherData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WeatherService {

    @POST("/api/weather")
    suspend fun getWeather() : Response<WeatherData>

    @POST("/api/weather")
    suspend fun getGeoWeather(@Body geoWeatherModel: GeoWeatherModel) : Response<WeatherData>

}