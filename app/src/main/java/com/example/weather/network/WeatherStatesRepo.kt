package com.example.weather.network

import com.example.weather.model.CitySearchData
import com.example.weather.model.FormattedCity
import com.example.weather.model.WeatherLocationStates

interface WeatherStatesRepo {
    suspend fun getStates(countryCode: String, apikey: String) : List<WeatherLocationStates>
}

class NetworkStates(
    private val weatherStatesApi: WeatherStatesApi
): WeatherStatesRepo{
    override suspend fun getStates(
        countryCode: String,
        apikey: String
    ): List<WeatherLocationStates> = weatherStatesApi.getStatesData(countryCode, apikey)

}