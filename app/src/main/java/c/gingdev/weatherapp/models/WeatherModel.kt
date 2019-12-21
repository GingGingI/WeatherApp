package c.gingdev.weatherapp.models

data class WeatherModel(val consolidated_weather: List<DayWeather>)

data class DayWeather(
    val id: String,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String,
    val created: String,
    val applicable_date: String,
    val min_temp: Float,
    val max_temp: Float,
    val the_temp: Float,
    val wind_speed: Float,
    val wind_direction: Float,
    val air_pressure: Float,
    val humidity: Int,
    val visibility: Float,
    val predictability: Int)