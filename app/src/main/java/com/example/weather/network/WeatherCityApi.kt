package com.example.weather.network

import com.example.weather.model.CitySearchData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherCityApi {
    @GET("/locations/v1/{countryCode}/{adminCode}/search")
    suspend fun getCityData(
        @Path("countryCode") countryCode: String,
        @Path("adminCode") adminCode: String,
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ) : List<CitySearchData>
}