package com.example.indianicpractical.ui.weatherinfo

data class WeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val main: WeatherInfo
)

data class Weather(
    val main: String,
    val description: String
)

data class WeatherInfo(
    val temp: Double,
    val humidity: Int
)