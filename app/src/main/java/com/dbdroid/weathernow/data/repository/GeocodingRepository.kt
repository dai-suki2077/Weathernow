package com.dbdroid.weathernow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbdroid.weathernow.data.api.GeocodingService
import com.dbdroid.weathernow.models.GeocodingData.GeocodingModel
import com.dbdroid.weathernow.models.NewLocationModel

class GeocodingRepository(private val geocodingService: GeocodingService) {

    private val locationsLiveData = MutableLiveData<GeocodingModel?>()

    val locationsData: LiveData<GeocodingModel?>
        get() = locationsLiveData

    suspend fun getLocations(newLocationDataModel: NewLocationModel) {
        val locationResult = geocodingService.getLocations(newLocationDataModel)

        if(locationResult.isSuccessful && locationResult.body() != null) {
            locationsLiveData.postValue(locationResult.body())
        } else {
            locationsLiveData.postValue(null)
        }
    }

}