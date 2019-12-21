package c.gingdev.weatherapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {
    private val serverUrl: String = "https://www.metaweather.com"
    val retrofit: Retrofit by lazy {
        provideRetrofit()
    }

//    retrofit
    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(serverUrl)
            .build()
    }

//    services
    fun getWeatherService(): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}