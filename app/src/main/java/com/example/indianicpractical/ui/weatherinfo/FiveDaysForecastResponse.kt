package com.example.indianicpractical.ui.weatherinfo

data class FiveDaysForecastResponse(
    val city: City,
    val list: List<Forecast>
)
data class City(
    val name: String
)

data class Forecast(
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
)
data class Main(
    val temp: Double,
    val humidity: Int
)
