package com.example.weather.model

import com.example.weather.model.CitySearchData.AdministrativeArea
import com.example.weather.model.CitySearchData.Country
import com.example.weather.model.CitySearchData.SupplementalAdminArea

data class FormattedCity(
    val localizedName: String,
    val englishName: String,
    val key: String,
    val primaryPostalCode: String?,
    val country: Country,
    val supplementalAdminAreas: List<SupplementalAdminArea>,
    val administrativeArea: AdministrativeArea,
)
