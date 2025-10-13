package hy.uth.tuan03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hy.uth.tuan03.ui.*
import hy.uth.tuan03.ui.navigation.Screen
import hy.uth.tuan03.ui.theme.Tuan03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tuan03Theme { AppNavigation() }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Intro.route
    ) {
        composable(Screen.Intro.route) {
            IntroScreen(onStart = { navController.navigate(Screen.Components.route) })
        }
        composable(Screen.Components.route) {
            ComponentsListScreen(
                onOpen = { route -> navController.navigate(route) },
                onBack = { navController.popBackStack() }
            )
        }

        // Chi tiết các components
        composable(Screen.Text.route) { TextScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Image.route) { ImageScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.TextField.route) { TextFieldScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.Layout.route) { RowColumnScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.ButtonSwitch.route) { ButtonSwitchScreen(onBack = { navController.popBackStack() }) }
        composable(Screen.CardCheckboxSlider.route) { CardCheckboxSliderScreen(onBack = { navController.popBackStack() }) }
    }
}
