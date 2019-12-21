package c.gingdev.weatherapp.constructors

import c.gingdev.weatherapp.models.LocationModel
import c.gingdev.weatherapp.models.WeatherModel

interface MainActivityConstructor {
    interface View {
        fun searchStarted()
        fun addData(location: LocationModel, weatherModel: WeatherModel)
        fun searchEnd()
    }
    interface Presenter {
        val view: View

        fun requestWeatherData(keyword: String)
    }
}