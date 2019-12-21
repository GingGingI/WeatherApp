package c.gingdev.weatherapp.constructors

interface MainActivityConstructor {
    interface View {

    }
    interface Presenter {
        val view: View
    }
}