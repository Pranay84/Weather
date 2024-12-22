package com.example.weather.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    @SerializedName("DailyForecasts") val dailyForecasts: List<DailyForecast>,
    @SerializedName("Headline") val headline: Headline
) {
    data class DailyForecast(
        @SerializedName("Date") val date: String,
        @SerializedName("Day") val day: Day,
        @SerializedName("EpochDate") val epochDate: Int,
        @SerializedName("Link") val link: String,
        @SerializedName("MobileLink") val mobileLink: String,
        @SerializedName("Night") val night: Night,
        @SerializedName("Sources") val sources: List<String>,
        @SerializedName("Temperature") val temperature: Temperature
    ) {
        data class Day(
            @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
            @SerializedName("Icon") val icon: Int,
            @SerializedName("IconPhrase") val iconPhrase: String,
            @SerializedName("PrecipitationIntensity") val precipitationIntensity: String?,
            @SerializedName("PrecipitationType") val precipitationType: String?
        )

        data class Night(
            @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
            @SerializedName("Icon") val icon: Int,
            @SerializedName("IconPhrase") val iconPhrase: String,
            @SerializedName("PrecipitationIntensity") val precipitationIntensity: String?,
            @SerializedName("PrecipitationType") val precipitationType: String?
        )

        data class Temperature(
            @SerializedName("Maximum") val maximum: Maximum,
            @SerializedName("Minimum") val minimum: Minimum
        ) {
            data class Maximum(
                @SerializedName("Unit") val unit: String,
                @SerializedName("UnitType") val unitType: Int,
                @SerializedName("Value") val value: Int
            )

            data class Minimum(
                @SerializedName("Unit") val unit: String,
                @SerializedName("UnitType") val unitType: Int,
                @SerializedName("Value") val value: Int
            )
        }
    }

    data class Headline(
        val Category: String,
        val EffectiveDate: String,
        val EffectiveEpochDate: Int,
        val EndDate: String,
        val EndEpochDate: Int,
        val Link: String,
        val MobileLink: String,
        val Severity: Int,
        val Text: String
    )
}