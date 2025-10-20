package hy.uth.tuan04bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import hy.uth.tuan04bai2.screens.*
import hy.uth.tuan04bai2.ui.theme.Tuan04bai2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tuan04bai2Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "forget_password") {
                    composable("forget_password") {
                        ForgetPasswordScreen(navController)
                    }
                    composable(
                        "verify_code/{email}",
                        arguments = listOf(navArgument("email") { type = NavType.StringType })
                    ) {
                        VerifyCodeScreen(navController, it.arguments?.getString("email"))
                    }
                    composable(
                        "create_password/{email}/{code}",
                        arguments = listOf(
                            navArgument("email") { type = NavType.StringType },
                            navArgument("code") { type = NavType.StringType }
                        )
                    ) {
                        CreatePasswordScreen(
                            navController,
                            it.arguments?.getString("email"),
                            it.arguments?.getString("code")
                        )
                    }
                    composable(
                        "confirm/{email}/{code}/{password}",
                        arguments = listOf(
                            navArgument("email") { type = NavType.StringType },
                            navArgument("code") { type = NavType.StringType },
                            navArgument("password") { type = NavType.StringType }
                        )
                    ) {
                        ConfirmScreen(
                            it.arguments?.getString("email"),
                            it.arguments?.getString("code"),
                            it.arguments?.getString("password")
                        )
                    }
                }
            }
        }
    }
}
