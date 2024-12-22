package com.example.weather.data

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.weather.WeatherStatesApp
import io.github.cdimascio.dotenv.Dotenv
import com.example.weather.model.CitySearchData
import com.example.weather.model.FormattedCity
import com.example.weather.model.FormattedForeCast
import com.example.weather.model.WeatherLocationStates
import com.example.weather.network.WeatherCityRepo
import com.example.weather.network.WeatherForecastRepo
import com.example.weather.network.WeatherStatesRepo
import com.google.firebase.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.io.IOException

interface StatesUi {
    data class Success(val states: List<WeatherLocationStates> ): StatesUi
    object Loading: StatesUi
    object Error: StatesUi
}

interface CityUi {
    data class Success(val cities: List<FormattedCity>): CityUi
    object Loading: CityUi
    object Error: CityUi
}

interface WeatherUi {
    data class Success(val forecast: List<FormattedForeCast>): WeatherUi
    object Loading: WeatherUi
    object Error: WeatherUi
}

class WeatherStatesViewModel(
    private val statesRepo: WeatherStatesRepo,
    private val cityRepo: WeatherCityRepo,
    private val forecastRepo: WeatherForecastRepo
): ViewModel() {
    var stateUi: StatesUi by mutableStateOf(StatesUi.Loading)
        private set

    var cityUi: CityUi by mutableStateOf(CityUi.Loading)
        private set

    var weatherUi: WeatherUi by mutableStateOf(WeatherUi.Loading)
        private set

    private val _locationUi = MutableStateFlow(LocationsUi())
    val locationUi: StateFlow<LocationsUi> = _locationUi

    lateinit var returnData: List<WeatherLocationStates>
    lateinit var returnData2: List<FormattedCity>
    lateinit var returnData3: List<FormattedForeCast>

    // val api_key = BuildConfig.
    val api_key = com.example.weather.BuildConfig.API_KEY


    fun onLocationSelect(location: String) {
        _locationUi.update { currentState ->
            currentState.copy(
                cityName = location
            )
        }
    }

    fun onStateSelect(stateName: String, stateId: String){
        _locationUi.update { currentState ->
            currentState.copy(
                stateName = stateName,
                stateId = stateId
            )
        }
    }

    fun onCitySearch() {
        _locationUi.update { currentState ->
            currentState.copy(
                buttonClicked = true
            )
        }

        viewModelScope.launch {
            cityViewModel(
                _locationUi.value.stateId,
                _locationUi.value.cityName
            )
        }
    }

    fun onCitySelect(id: String) {
        _locationUi.update { currentState ->
            currentState.copy(
                cityId = id
            )
        }

        viewModelScope.launch {
            weatherViewModel(
                _locationUi.value.cityId
            )
        }
    }


    init {
        firstViewModel()
    }

    fun firstViewModel() {
        viewModelScope.launch {
            statesViewModel()
        }
    }


    private suspend fun statesViewModel(): List<WeatherLocationStates>{
        stateUi = StatesUi.Loading
        stateUi = try {
            returnData = statesRepo.getStates("IN", "${api_key}")
            StatesUi.Success(returnData)
        } catch (e: IOException) {
            StatesUi.Error
        } catch (e: HttpException) {
            StatesUi.Error
        }

        return returnData
    }

    private suspend fun cityViewModel(stateId: String?, cityName: String?): List<FormattedCity> {
        /*
        val stateId = _locationUi.value?.stateId
        val cityName = _locationUi.value?.cityName

         */
        Log.i("Data Sent", "${stateId}, ${cityName}, ${_locationUi.value.buttonClicked}")
        cityUi = CityUi.Loading
        if (stateId != null && cityName != null) {
            cityUi = try {
                returnData2 = cityRepo.getCity(
                    "IN", stateId, "${api_key}", cityName
                )
                CityUi.Success(returnData2)
            } catch (e: IOException) {
                CityUi.Error
            } catch (e: HttpException) {
                CityUi.Error
            }

        } else {
            Log.i("Null Validation2", "Data Should be Entered")
        }

        return returnData2
    }

    private suspend fun weatherViewModel(cityId: String?): List<FormattedForeCast> {
        weatherUi = WeatherUi.Loading
        if (cityId != null) {
            weatherUi = try {
                returnData3 = forecastRepo.getForecastData(
                    cityId, "${api_key}")
                WeatherUi.Success(returnData3)
            } catch (e: IOException) {
                WeatherUi.Error
            } catch (e: HttpException) {
                WeatherUi.Error
            }
        }else {
            Log.i("Null Validation2", "Data Should be Entered")
        }

        return returnData3
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherStatesApp)
                Log.d("Application Key", "$application")
                val statesRepo = application.container.weatherStatesLocationRepo
                val cityRepo = application.container.weatherCityListRepo
                val forecastRepo = application.container.weatherForecastRepo
                WeatherStatesViewModel(statesRepo, cityRepo, forecastRepo)
            }
        }
    }
}