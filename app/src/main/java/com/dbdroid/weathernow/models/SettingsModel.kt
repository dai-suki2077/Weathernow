package com.dbdroid.weathernow.models

data class SettingsModel(
    var tempUnit: String = "celsius",
    var windSpeedUnit: String = "kmh",
    var pressureUnit: String = "hpa",
    var isMusicOn: Boolean = false
)