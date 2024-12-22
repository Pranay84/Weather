package com.example.weather.network

import com.example.weather.model.FormattedForeCast
import com.example.weather.model.WeatherForecast

interface WeatherForecastRepo {
    suspend fun getForecastData(locationKey: String, apikey: String): List<FormattedForeCast>
}

class NetworkForecast(private val foreCastApi: WeatherForeCastApi): WeatherForecastRepo{
    override suspend fun getForecastData(
        locationKey: String,
        apikey: String
    ): List<FormattedForeCast> {
        return foreCastApi.getWeatherForeCast(locationKey, apikey).dailyForecasts.map { data ->
            FormattedForeCast(
                Date = data.date,
                Day = data.day,
                Night = data.night,
                Temperature = data.temperature
            )
        }
    }
}