package com.example.weather.network

import com.example.weather.model.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherForeCastApi {
    @GET("/forecasts/v1/daily/5day/{locationKey}")
    suspend fun getWeatherForeCast(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String
    ): WeatherForecast
}