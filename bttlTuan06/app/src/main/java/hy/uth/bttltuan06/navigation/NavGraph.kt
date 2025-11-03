package hy.uth.bttltuan06.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import hy.uth.bttltuan06.ui.detail.TaskDetailScreen
import hy.uth.bttltuan06.ui.home.HomeScreen
import hy.uth.bttltuan06.viewmodel.TaskViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{taskId}") {
        fun createRoute(taskId: Int) = "detail/$taskId"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: TaskViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onTaskClick = { taskId ->
                    navController.navigate(Screen.Detail.createRoute(taskId))
                }
            )
        }
        
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                NavArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(
                taskId = taskId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onDeleteSuccess = {
                    navController.popBackStack()
                }
            )
        }
    }
}

