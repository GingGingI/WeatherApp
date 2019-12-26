package c.gingdev.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import c.gingdev.weatherapp.constructors.MainActivityConstructor
import c.gingdev.weatherapp.models.LocationModel
import c.gingdev.weatherapp.models.WeatherModel
import c.gingdev.weatherapp.presents.MainActivityPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_row_weather.view.*

class MainActivity : AppCompatActivity()
    , MainActivityConstructor.View
    , SwipeRefreshLayout.OnRefreshListener {

    private val presenter: MainActivityPresenter by lazy {
        MainActivityPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout.setOnRefreshListener(this)
        presenter.requestWeatherData("se")
    }

//    layout
    private var isLoading: Boolean = false
    private fun setLoadingState(state: Boolean) {
        isLoading = state
        tableLayout.visibility = if (isLoading) { View.GONE } else { View.VISIBLE }
        if (!refreshLayout.isRefreshing)
            LoadingView.visibility = if (isLoading) { View.VISIBLE } else { View.GONE }
    }

    override fun onRefresh() {
        presenter.requestWeatherData("se")
    }

//    presenter
    override fun searchStarted() {
        setLoadingState(true)
        clearLayout()
        createHeader()
    }

    override fun addData(location: LocationModel, weatherModel: WeatherModel) {
        createRow(location, weatherModel)
    }

    override fun searchEnd() {
        refreshLayout.isRefreshing = false
        setLoadingState(false)
    }

    fun clearLayout() {
        tableLayout.removeAllViews()
    }

//    header
    private fun createHeader() {
        val row = TableRow(this)
        val view = LayoutInflater.from(this).inflate(R.layout.layout_row_header, row, false)

        tableLayout.addView(view)
    }
//    row
    private fun createRow(location: LocationModel, weatherModel: WeatherModel) {
        val row = TableRow(this)
        val view = LayoutInflater.from(this).inflate(R.layout.layout_row_weather, row, false)

        view.apply {
//            location
            this.location.text = location.title

//            data
            val todayData = weatherModel.consolidated_weather[0]
            val tomorrowData = weatherModel.consolidated_weather[1]

//            today
            setWeatherIcon(view.todayWeatherIcon, todayData.weather_state_abbr)
            view.todayWeatherState.text = todayData.weather_state_name
            view.todayWeatherTemp.text = todayData.the_temp.stringTemp()
            view.todayWeatherHumidity.text = todayData.humidity.stringHumidity()

//            tomorrow

            setWeatherIcon(view.tomorrowWeatherIcon, tomorrowData.weather_state_abbr)
            view.tomorrowWeatherState.text = tomorrowData.weather_state_name
            view.tomorrowWeatherTemp.text = tomorrowData.the_temp.stringTemp()
            view.tomorrowWeatherHumidity.text = tomorrowData.humidity.stringHumidity()
        }.also {
            tableLayout.addView(it)
        }
    }

    private fun Float.stringTemp(): String {
        return String.format("%.0f℃", this)
    }
    private fun Int.stringHumidity(): String {
        return String.format("%d%%", this)
    }

    private fun setWeatherIcon(view: ImageView, weatherState: String) {
        Glide.with(this)
            .load("https://www.metaweather.com/static/img/weather/png/64/${weatherState}.png")
            .into(view)
    }
}
