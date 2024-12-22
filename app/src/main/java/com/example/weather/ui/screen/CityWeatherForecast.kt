package com.example.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.WeatherStatesViewModel
import com.example.weather.data.WeatherUi
import com.example.weather.model.FormattedForeCast
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CityWeatherForecast(viewModel: WeatherStatesViewModel, modifier: Modifier) {
    when(viewModel.weatherUi) {
        is WeatherUi.Success -> WeatherSuccessScreen(
            (viewModel.weatherUi as WeatherUi.Success).forecast,
            viewModel
        )
        is WeatherUi.Loading -> WeatherLoadingScreen()
        else -> WeatherErrorScreen()
    }

}

@Composable
fun WeatherErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_broken_image_24),
            contentDescription = stringResource(R.string.error)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.error)
        )
    }
}

@Composable
fun WeatherLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.loading1),
            contentDescription = stringResource(R.string.loading)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.loading)
        )
    }
}

@Composable
fun WeatherSuccessScreen(forecast: List<FormattedForeCast>, viewModel: WeatherStatesViewModel) {
    val locationsUiState = viewModel.locationUi.collectAsState()

    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box {
            Column {
                Text(
                    text = "${locationsUiState.value.cityName}"
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${locationsUiState.value.cityName}"
                )

                LazyRow {
                    items(forecast) {item ->
                        DayForecast(
                            item
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun DayForecast(day: FormattedForeCast) {
    val date = day.Date
    val dateTime = ZonedDateTime.parse(date)
    val result = dateTime.withZoneSameInstant(ZoneId.of("UTC")).format(
        DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
    )
    ElevatedCard(
        modifier = Modifier
            .padding(6.dp)
    ) {
        Column {
            Text(
                text = "${result}"
            )

            Text(
                text = "${day.Day.iconPhrase}"
            )

            Text(
                text = "${day.Night.iconPhrase}"
            )

            Text(
                text = "Temperature " + "${day.Temperature.minimum.value}" + " - " + "${day.Temperature.maximum.value}" + " \u00B0F"
            )
        }
    }
}