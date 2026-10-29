package au.edu.jcu.assessment.utilityapp.viewmodel

import au.edu.jcu.assessment.utilityapp.data.WeatherInfo

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String? = null,
    // Settings
    val isFahrenheit: Boolean = false,
    val selectedCity: City = City.SINGAPORE
)

enum class City(val displayName: String, val lat: Double, val lon: Double) {
    SINGAPORE("Singapore", 1.2897, 103.8501),
    CAIRNS("Cairns", -16.9203, 145.7710),
    BRISBANE("Brisbane", -27.4698, 153.0251),
    SYDNEY("Sydney", -33.8688, 151.2093),
    TOKYO("Tokyo", 35.6762, 139.6503)
}