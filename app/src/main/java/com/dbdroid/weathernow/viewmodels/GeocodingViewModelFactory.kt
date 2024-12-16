package com.dbdroid.weathernow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dbdroid.weathernow.data.repository.GeocodingRepository

class GeocodingViewModelFactory(private val geocodingRepository: GeocodingRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GeocodingViewModel(geocodingRepository) as T
    }

}