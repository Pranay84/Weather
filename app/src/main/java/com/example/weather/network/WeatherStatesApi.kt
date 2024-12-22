package com.example.weather.network

import com.example.weather.model.CitySearchData
import com.example.weather.model.WeatherLocationStates
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherStatesApi {
    @GET("/locations/v1/adminareas/{countryCode}")
    suspend fun getStatesData(
        @Path("countryCode") countryCode: String,
        @Query("apikey") apikey: String
    ) : List<WeatherLocationStates>
}