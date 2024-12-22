package com.example.weather.network

import android.util.Log
import com.example.weather.model.FormattedCity

interface WeatherCityRepo {
    suspend fun getCity(countryCode: String, adminCode: String, apikey: String, q: String): List<FormattedCity>
}

class NetworkCities(private val citySearchApi: WeatherCityApi) : WeatherCityRepo{
    override suspend fun getCity(
        countryCode: String,
        adminCode: String,
        apikey: String,
        q: String
    ): List<FormattedCity> {
        return citySearchApi.getCityData(countryCode, adminCode, apikey, q).map { city ->
            Log.i("CountryCode", "${countryCode}")
            Log.i("AdminCode", "${adminCode}")
            Log.i("ApiKey", "${apikey}")
            Log.i("City", "${q}")
            Log.i("Cities: ", "${city}")
            Log.i("Search Data: ", "${citySearchApi.getCityData(countryCode, adminCode, apikey, q)}")
            FormattedCity(
                localizedName = city.localizedName,
                englishName = city.englishName,
                key = city.key,
                primaryPostalCode = city.primaryPostalCode,
                country = city.country,
                supplementalAdminAreas = city.supplementalAdminAreas,
                administrativeArea = city.administrativeArea
            )
        }
    }
}