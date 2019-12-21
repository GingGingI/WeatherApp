package c.gingdev.weatherapp.presents

import c.gingdev.weatherapp.constructors.MainActivityConstructor

class MainActivityPresenter(override val view: MainActivityConstructor.View)
    : MainActivityConstructor.Presenter {

}