package c.gingdev.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import c.gingdev.weatherapp.constructors.MainActivityConstructor
import c.gingdev.weatherapp.presents.MainActivityPresenter

class MainActivity : AppCompatActivity()
    , MainActivityConstructor.View {

    private val presenter: MainActivityPresenter by lazy {
        MainActivityPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
