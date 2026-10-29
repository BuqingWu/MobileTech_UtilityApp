package au.edu.jcu.assessment.utilityapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String, lat: Double, lon: Double): NetworkResult<WeatherInfo>
}

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        cityName: String,
        lat: Double,
        lon: Double
    ): NetworkResult<WeatherInfo> = withContext(dispatcher) {
        try {
            val response = api.fetchWeather(latitude = lat, longitude = lon)
            if (response.isSuccessful && response.body() != null) {
                val current = response.body()!!.current
                val condition = parseWeatherCode(current.weatherCode)
                val advice = generateAdvice(condition, current.temperature)

                NetworkResult.Success(
                    WeatherInfo(
                        cityName = cityName,
                        temperature = current.temperature,
                        humidity = current.humidity,
                        windSpeed = current.windSpeed,
                        condition = condition,
                        advice = advice
                    )
                )
            } else {
                NetworkResult.Error("API response error: ${response.code()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to connect to weather server")
        }
    }

    private fun parseWeatherCode(code: Int): String = when (code) {
        0 -> "Clear Sky"
        1, 2, 3 -> "Partly Cloudy"
        45, 48 -> "Foggy"
        51, 53, 55, 61, 63, 65 -> "Rainy"
        71, 73, 75 -> "Snowy"
        95, 96, 99 -> "Thunderstorm"
        else -> "Cloudy"
    }

    private fun generateAdvice(condition: String, temp: Double): String = when {
        condition.contains("Rain") || condition.contains("Thunderstorm") -> "Grab an umbrella before heading out!"
        temp > 30.0 -> "Hot outside! Stay hydrated and wear light clothes."
        temp < 15.0 -> "Chilly day! Don't forget your warm coat."
        else -> "Great weather for outdoor activities!"
    }
}