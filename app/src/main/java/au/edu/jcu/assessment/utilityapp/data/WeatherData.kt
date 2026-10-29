package au.edu.jcu.assessment.utilityapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    @SerialName("current")
    val current: CurrentWeatherUnits
)

@Serializable
data class CurrentWeatherUnits(
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("relative_humidity_2m")
    val humidity: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double,
    @SerialName("weather_code")
    val weatherCode: Int
)

data class WeatherInfo(
    val cityName: String,
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val condition: String,
    val advice: String
)