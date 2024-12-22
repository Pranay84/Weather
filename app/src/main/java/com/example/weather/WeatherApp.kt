@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.weather

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.decode.DataSource
import com.example.weather.data.WeatherStatesViewModel
import com.example.weather.ui.screen.HomeScreen

enum class WeatherPages(@StringRes val title: Int) {
    Start(title = R.string.start_screen),
    City(title = R.string.city_screen),
    Weather(title = R.string.weather_screen)
}

@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    val statesViewModel: WeatherStatesViewModel = viewModel(factory = WeatherStatesViewModel.Factory)

    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = WeatherPages.valueOf(
        backStackEntry?.destination?.route ?: WeatherPages.Start.name
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()}
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeScreen(
                statesUi = statesViewModel.stateUi,
                modifier = modifier
            )
        }
    }
}


@Composable
fun WeatherAppBar(
    currentScreen: WeatherPages,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}