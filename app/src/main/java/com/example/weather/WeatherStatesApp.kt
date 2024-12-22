package com.example.weather

import android.app.Application
import com.example.weather.network.AppContainer
import com.example.weather.network.DefaultStates

class WeatherStatesApp: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultStates()
    }
}