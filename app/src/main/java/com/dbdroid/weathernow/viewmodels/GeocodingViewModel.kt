package com.dbdroid.weathernow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.dbdroid.weathernow.data.repository.GeocodingRepository
import com.dbdroid.weathernow.models.GeocodingData.GeocodingModel
import com.dbdroid.weathernow.models.NewLocationModel

class GeocodingViewModel(private val geocodingRepository: GeocodingRepository) : ViewModel() {

    fun getLocations(newLocationDataModel: NewLocationModel, coroutineExceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            geocodingRepository.getLocations(newLocationDataModel)
        }
    }

    val locationLiveData : LiveData<GeocodingModel?>
        get() = geocodingRepository.locationsData

}