package c.gingdev.weatherapp.utils

import c.gingdev.weatherapp.models.LocationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/api/location/search/")
    fun getLocations(@Query("query") keyword: String): Call<List<LocationModel>>
}