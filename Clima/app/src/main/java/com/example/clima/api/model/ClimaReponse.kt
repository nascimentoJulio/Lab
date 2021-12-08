package com.example.clima.api.model

data class ClimaReponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)