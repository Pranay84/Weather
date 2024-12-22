package com.example.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.data.StatesUi
import com.example.weather.R
import com.example.weather.WeatherPages
import com.example.weather.data.WeatherStatesViewModel

@Composable
fun HomeScreen(
    statesUi: StatesUi,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    when(statesUi) {
        is StatesUi.Success -> {
            val statesViewModel: WeatherStatesViewModel = viewModel(factory = WeatherStatesViewModel.Factory)


            NavHost(
                navController = navController,
                startDestination = WeatherPages.Start.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
            ) {
                composable(route = WeatherPages.Start.name){
                    SuccessScreen(
                        modifier,
                        states = statesUi.states,
                        onItemClick = {stateName, stateId ->
                            statesViewModel.onStateSelect(stateName, stateId)
                            navController.navigate(WeatherPages.City.name)
                        }
                    )
                }
                composable(route = WeatherPages.City.name) {
                    CitySearch(
                        viewModel = statesViewModel,
                        onItemClick = {key ->
                            statesViewModel.onCitySelect(key)
                            navController.navigate(WeatherPages.Weather.name)
                        }
                    )
                }
                composable(route = WeatherPages.Weather.name) {
                    CityWeatherForecast(
                        viewModel = statesViewModel,
                        modifier
                    )
                }
            }
        }
        is StatesUi.Loading -> LoadingScreen()
        else -> ErrorScreen()
    }
}

@Composable
fun ErrorScreen() {
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
fun LoadingScreen() {
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