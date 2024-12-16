package com.dbdroid.weathernow.data.api

import com.dbdroid.weathernow.models.GeocodingData.GeocodingModel
import com.dbdroid.weathernow.models.NewLocationModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GeocodingService {

    @POST("/api/geocoding")
    suspend fun getLocations(@Body newLocationDataModel: NewLocationModel) : Response<GeocodingModel>

}