package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class CitySearchData(
        @SerializedName("AdministrativeArea") val administrativeArea: AdministrativeArea,
        @SerializedName("Country") val country: Country,
        @SerializedName("DataSets") val cataSets: List<String>,
        @SerializedName("EnglishName") val englishName: String,
        @SerializedName("GeoPosition") val geoPosition: GeoPosition,
        @SerializedName("IsAlias") val isAlias: Boolean,
        @SerializedName("Key") val key: String,
        @SerializedName("LocalizedName") val localizedName: String,
        @SerializedName("PrimaryPostalCode") val primaryPostalCode: String,
        @SerializedName("Rank") val rank: Int,
        @SerializedName("Region") val region: Region,
        @SerializedName("SupplementalAdminAreas") val supplementalAdminAreas: List<SupplementalAdminArea>,
        @SerializedName("TimeZone") val timeZone: TimeZone,
        @SerializedName("Type") val type: String,
        @SerializedName("Version") val version: Int
    ) {
        data class AdministrativeArea(
            @SerializedName("CountryID") val countryID: String,
            @SerializedName("EnglishName") val englishName: String,
            @SerializedName("EnglishType") val englishType: String,
            @SerializedName("ID") val id: String,
            @SerializedName("Level") val level: Int,
            @SerializedName("LocalizedName") val localizedName: String,
            @SerializedName("LocalizedType") val localizedType: String
        )
    
        data class Country(
            @SerializedName("EnglishName") val englishName: String,
            @SerializedName("ID") val id: String,
            @SerializedName("LocalizedName") val localizedName: String
        )
    
        data class GeoPosition(
            @SerializedName("Elevation") val elevation: Elevation,
            @SerializedName("Latitude") val latitude: Double,
            @SerializedName("Longitude") val longitude: Double
        ) {
            data class Elevation(
                @SerializedName("Imperial") val imperial: Imperial,
                @SerializedName("Metric") val metric: Metric
            ) {
                data class Imperial(
                    @SerializedName("Unit") val unit: String,
                    @SerializedName("UnitType") val unitType: Int,
                    @SerializedName("Value") val value: Double
                )
    
                data class Metric(
                    @SerializedName("Unit") val unit: String,
                    @SerializedName("UnitType") val unitType: Int,
                    @SerializedName("Value") val value: Double
                )
            }
        }
    
        data class Region(
            @SerializedName("EnglishName") val englishName: String,
            @SerializedName("ID") val id: String,
            @SerializedName("LocalizedName") val localizedName: String
        )
    
        data class SupplementalAdminArea(
            @SerializedName("EnglishName") val englishName: String,
            @SerializedName("Level") val level: Int,
            @SerializedName("LocalizedName") val localizedName: String
        )
    
        data class TimeZone(
            @SerializedName("Code") val code: String,
            @SerializedName("GmtOffset") val gmtOffset: Double,
            @SerializedName("IsDaylightSaving") val isDaylightSaving: Boolean,
            @SerializedName("Name") val name: String,
            @SerializedName("NextOffsetChange") val nextOffsetChange: Any?
        )
    }