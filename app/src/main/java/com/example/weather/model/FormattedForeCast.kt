package com.example.weather.model

data class FormattedForeCast(
    val Date: String,
    val Day: WeatherForecast.DailyForecast.Day,
    val Night: WeatherForecast.DailyForecast.Night,
    val Temperature: WeatherForecast.DailyForecast.Temperature
)
