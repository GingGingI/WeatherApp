package c.gingdev.weatherapp.models

data class LocationModel(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String)