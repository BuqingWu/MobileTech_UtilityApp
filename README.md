# MobileTech Utility App
# Weather Utility App (CP3406 Assessment 1 by WuBuqing)

An "at-a-glance" daily weather utility application for Android built with Jetpack Compose, Material Design 3, and Modern Android Architecture (MVVM + Repository Pattern).

---

## Features
- **Live Weather Dashboard (UtilityScreen)**:
    - Fetches real-time temperature, relative humidity, wind speed, and WMO weather condition codes using the Open-Meteo REST API.
    - Actionable lifestyle and outdoor advice (e.g., umbrella alerts, hydration reminders, cold/heat warnings) displayed in an "at-a-glance" Material 3 card.
    - Manual data refresh triggered via the top app bar action icon.
- **Dynamic User Preferences (SettingsScreen)**:
    - Reactive unit toggle: Seamlessly switch between Celsius (°C) and Fahrenheit (°F) with immediate UI recalculation.
    - Default city selector: Switch coordinates between Singapore, Cairns, Brisbane, Sydney, and Tokyo to automatically trigger fresh weather forecasts.
- **Robust User Feedback**:
    - Material 3 loading indicator during asynchronous network calls.
    - Integrated error state display with a retry button for network interruptions.

---

## App Architecture & Tech Stack

The project adheres to the recommended Android Architecture guidelines and separation of concerns:

- **UI Layer (View)**:
    - Declarative UI built entirely with **Jetpack Compose** and **Material Design 3** (`Scaffold`, `TopAppBar`, `NavigationBar`, `ElevatedCard`).
    - State observation via `collectAsState()` on Kotlin `StateFlow`.
- **ViewModel Layer**:
    - `WeatherViewModel`: Manages UI state (`WeatherUiState`) using `MutableStateFlow` and executes coroutines via `viewModelScope`.
- **Data Layer (Repository & Remote API)**:
    - **Repository Pattern**: `WeatherRepository` abstracts API data fetching from the presentation layer.
    - **Networking**: `Retrofit 2` + `OkHttp 3`.
    - **Serialization**: `Kotlinx Serialization JSON` with type-safe response data classes (`WeatherApiResponse`).
- **Dependency Injection**:
    - Powered by **Koin** (`koin-android`, `koin-androidx-compose`) declaring singleton dependencies for Dispatchers, Retrofit, API interfaces, and ViewModels.

---

## Key Concepts & Weekly Mapping

| Topic / Lecture | Implementation in Project |
|:---|:---|
| **Week 1-2: Kotlin & Jetpack Compose** | Declarative layout components (`Column`, `Row`, `Box`, `Scaffold`, `NavigationBar`) in `MainActivity.kt`. |
| **Week 3: Material Design 3** | M3 color tokens, `TopAppBar`, `ElevatedCard`, `Switch`, `RadioButton`, and semantic typography. |
| **Week 4: MVVM & Dependency Injection** | Unidirectional Data Flow (UDF), `WeatherViewModel`, and Koin DI modules (`Modules.kt`, `UtilityAppApplication.kt`). |
| **Week 5: Coroutines & Retrofit** | Asynchronous network calls via `Dispatchers.IO`, Open-Meteo API integration, and sealed `NetworkResult`. |

---

## Getting Started

### Prerequisites
- Android Studio Ladybug / Iguana or later
- Android SDK with Minimum API 24 (Target API 34+)
- Active Internet connection for live weather fetching

### How to Run
1. Clone or download this repository.
2. Open the project in Android Studio and let Gradle sync dependencies.
3. Select an Android emulator (or connected physical device) and click the green **Run 'app'** button.
