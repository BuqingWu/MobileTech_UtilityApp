package au.edu.jcu.assessment.utilityapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.jcu.assessment.utilityapp.data.NetworkResult
import au.edu.jcu.assessment.utilityapp.data.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        val city = _uiState.value.selectedCity
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            when (val result = repository.getCurrentWeather(city.displayName, city.lat, city.lon)) {
                is NetworkResult.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, weatherInfo = result.data, error = null)
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun toggleTemperatureUnit(isFahrenheit: Boolean) {
        _uiState.update { it.copy(isFahrenheit = isFahrenheit) }
    }

    fun selectCity(city: City) {
        if (_uiState.value.selectedCity != city) {
            _uiState.update { it.copy(selectedCity = city) }
            fetchWeather()
        }
    }
}