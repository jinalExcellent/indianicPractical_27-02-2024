package com.example.indianicpractical.network

import com.example.indianicpractical.ui.weatherinfo.FiveDaysForecastResponse
import com.example.indianicpractical.ui.weatherinfo.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForeCast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): FiveDaysForecastResponse
}