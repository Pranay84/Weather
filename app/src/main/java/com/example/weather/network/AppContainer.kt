package com.example.weather.network

import com.example.weather.model.WeatherLocationStates
import okhttp3.OkHttp
import androidx.compose.runtime.getValue
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val weatherStatesLocationRepo: WeatherStatesRepo
    val weatherCityListRepo: WeatherCityRepo
    val weatherForecastRepo: WeatherForecastRepo
}

class DefaultStates: AppContainer{
    private val baseUrl = "https://dataservice.accuweather.com"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val statesLocation: WeatherStatesApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherStatesApi::class.java)
    }

    val citySearch: WeatherCityApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherCityApi::class.java)
    }

    val weatherForecast: WeatherForeCastApi  by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherForeCastApi::class.java)
    }

    override val weatherStatesLocationRepo: WeatherStatesRepo by lazy {
        NetworkStates(statesLocation)
    }

    override val weatherCityListRepo: WeatherCityRepo by lazy {
        NetworkCities(citySearch)
    }

    override val weatherForecastRepo: WeatherForecastRepo by lazy {
        NetworkForecast(weatherForecast)
    }
}