package c.gingdev.weatherapp.presents

import android.util.Log
import c.gingdev.weatherapp.constructors.MainActivityConstructor
import c.gingdev.weatherapp.models.LocationModel
import c.gingdev.weatherapp.models.WeatherModel
import c.gingdev.weatherapp.utils.RetrofitUtils
import c.gingdev.weatherapp.utils.WeatherAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(override val view: MainActivityConstructor.View)
    : MainActivityConstructor.Presenter {
    private val TAG = "Main-presenter"

    private val weatherAPIService: WeatherAPIService by lazy {
        RetrofitUtils().getWeatherService()
    }
    override fun requestWeatherData(keyword: String) {
        view.searchStarted()
        getLocations(keyword)
        view.searchEnd()
    }

    private fun getLocations(keyword: String) {
        weatherAPIService.locations(keyword, { _, t ->
//            onError
            Log.e(TAG, "error-Location : ${t.message}")
        }, { _, response ->
//            onResponse
            val locations: List<LocationModel>? = response.body()

            locations?.run {
                forEach {
                    getWeatherByLocation(it)
                }
            }
        })
    }

    private fun getWeatherByLocation(location: LocationModel) {
        weatherAPIService.weathers(location.woeid, {_, t ->
            //            onError
            Log.e(TAG, "error-Weather : ${t.message}")
        }, { _, response ->
            response.body()?.run {
                view.addData(location, this)
            }
        })
    }


//    location
    fun WeatherAPIService.locations(keyword: String,
                                 failure: (Call<List<LocationModel>>, Throwable) -> Unit,
                                 response: (Call<List<LocationModel>>, Response<List<LocationModel>>) -> Unit) {
        this.getLocations(keyword).enqueue(object: Callback<List<LocationModel>> {
            override fun onFailure(call: Call<List<LocationModel>>, t: Throwable) {
                failure(call, t)
            }

            override fun onResponse(
                call: Call<List<LocationModel>>,
                response: Response<List<LocationModel>>) {
                response(call, response)
            }
        })
    }

//    weather
    fun WeatherAPIService.weathers(woeid: Int,
                                   failure: (Call<WeatherModel>, Throwable) -> Unit,
                                   response: (Call<WeatherModel>, Response<WeatherModel>) -> Unit) {
        this.getWeather(woeid).enqueue(object: Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                failure(call, t)
            }

            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>) {
                response(call, response)
            }
        })
    }
}