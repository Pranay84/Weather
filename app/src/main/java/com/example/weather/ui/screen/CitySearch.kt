package com.example.weather.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.R
import com.example.weather.data.CityUi
import com.example.weather.data.WeatherStatesViewModel
import com.example.weather.model.FormattedCity

@Composable
fun CitySearch(
    viewModel: WeatherStatesViewModel,
    onItemClick: (String) -> Unit
) {
    val locationsUiState = viewModel.locationUi.collectAsState()
    val cityUi = viewModel.cityUi

    Column{
        Text(
            text = "${locationsUiState.value.stateName}",
            modifier = Modifier
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "${locationsUiState.value.cityName}",
                onValueChange = {viewModel.onLocationSelect(it)},
                label = { Text("City") },
                placeholder = { Text("City Name") }
            )

            Spacer(modifier = Modifier.width(5.dp))

            Button(
                onClick = {viewModel.onCitySearch()},
                modifier = Modifier
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = "Search"
                )
            }
        }

        when(cityUi) {
            is CityUi.Success -> CitySuccessScreen(
                cities = cityUi.cities,
                onItemClick
            )
            is CityUi.Loading -> CityLoadingScreen()
            else -> CityErrorScreen()
        }
    }
}

@Composable
fun CityErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
fun CityLoadingScreen() {
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
fun CitySuccessScreen(
    cities: List<FormattedCity>,
    onItemClick: (String) -> Unit
) {
    LazyColumn {
        items(cities) {city ->
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .width(450.dp),
                onClick = { onItemClick(city.key) }
            ) {
                Column(
                    modifier = Modifier
                        .padding(6.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Official Name: ${if (city.englishName == null) "" else city.englishName}"
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "District: ${city?.supplementalAdminAreas?.get(0)?.englishName}"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Area: ${city.supplementalAdminAreas?.get(1)?.englishName}"
                    )

                    Text(
                        text = "State: ${city?.administrativeArea?.englishName}"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Country: ${city.country?.id}"
                    )
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun CityPreview() {
    CitySearch()
}
 */