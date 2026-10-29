package au.edu.jcu.assessment.utilityapp.di

import au.edu.jcu.assessment.utilityapp.data.WeatherApi
import au.edu.jcu.assessment.utilityapp.data.WeatherRepository
import au.edu.jcu.assessment.utilityapp.data.WeatherRepositoryImpl
import au.edu.jcu.assessment.utilityapp.viewmodel.WeatherViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    //Dispatcher
    single { Dispatchers.IO }

    //Retrofit (Open-Meteo API)
    single {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    //API
    single { get<Retrofit>().create(WeatherApi::class.java) }

    //Repository
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }

    //ViewModel
    viewModel { WeatherViewModel(get()) }
}