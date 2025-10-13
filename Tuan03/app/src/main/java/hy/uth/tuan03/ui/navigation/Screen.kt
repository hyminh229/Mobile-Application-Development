package hy.uth.tuan03.ui.navigation

sealed class Screen(val route: String) {
    data object Intro : Screen("intro")
    data object Components : Screen("components")

    data object Text : Screen("text")
    data object Image : Screen("image")
    data object TextField : Screen("textfield")
    data object Layout : Screen("layout")
    data object ButtonSwitch : Screen("buttonswitch")
    data object CardCheckboxSlider : Screen("cardcheckboxslider")
}
