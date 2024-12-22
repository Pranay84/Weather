package com.example.weather.ui.screen

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.model.WeatherLocationStates

@Composable
fun SuccessScreen(
    modifier: Modifier,
    states: List<WeatherLocationStates>,
    onItemClick: (String, String) -> Unit
) {
    LazyColumn {
        items(states) {state ->
            ElevatedCard(
                modifier = modifier
                    .fillMaxSize()
                    .padding(8.dp),
                onClick = { onItemClick(state.EnglishName, state.ID) }
            ) {
                Column(
                    modifier = modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Official Name: ${state.EnglishName}"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Type: ${state.LocalizedType}"
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Country: ${state.CountryID}"
                    )
                }
            }
        }
    }
}

/*
@Composable
fun StatesListScreen(
    state: WeatherLocationStates,
    modifier: Modifier,
    viewModel: WeatherStatesViewModel = viewModel(),
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        onClick = {viewModel.onStateSelect(state.EnglishName, state.ID)}
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = "Official Name: ${state.EnglishName}"
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Type: ${state.LocalizedType}"
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Country: ${state.CountryID}"
            )
        }

    }
}

 */