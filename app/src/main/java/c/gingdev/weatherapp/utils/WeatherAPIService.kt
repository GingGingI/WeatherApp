package c.gingdev.weatherapp.utils

import c.gingdev.weatherapp.models.LocationModel
import c.gingdev.weatherapp.models.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("/api/location/search/")
    fun getLocations(@Query("query") keyword: String): Call<List<LocationModel>>

    @GET("/api/location/{woeid}")
    fun getWeather(@Path("woeid") woeid: Int): Call<WeatherModel>
}