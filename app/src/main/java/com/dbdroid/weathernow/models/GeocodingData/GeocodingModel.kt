package com.dbdroid.weathernow.models.GeocodingData

data class GeocodingModel(
    val generationtime_ms: Double,
    val results: ArrayList<GeocodingResult>?
)