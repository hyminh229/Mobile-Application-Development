package hy.uth.btvntuan06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hy.uth.btvntuan06.data.di.NetworkModule
import hy.uth.btvntuan06.ui.screen.DetailScreen
import hy.uth.btvntuan06.ui.screen.ListScreen
import hy.uth.btvntuan06.ui.theme.BtvnTuan06Theme
import hy.uth.btvntuan06.ui.viewmodel.TaskDetailViewModel
import hy.uth.btvntuan06.ui.viewmodel.TaskListViewModel

/**
 * MainActivity - Entry point của ứng dụng
 * Sử dụng Navigation Compose để điều hướng giữa các màn hình
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Khởi tạo repository
        val repository = NetworkModule.repository
        
        setContent {
            BtvnTuan06Theme {
                val navController = rememberNavController()
                
                // ViewModels
                val listViewModel: TaskListViewModel = viewModel(
                    factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                            @Suppress("UNCHECKED_CAST")
                            return TaskListViewModel(repository) as T
                        }
                    }
                )
                
                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable("list") {
                        ListScreen(
                            viewModel = listViewModel,
                            onTaskClick = { taskId ->
                                navController.navigate("detail/$taskId")
                            }
                        )
                    }
                    
                    composable("detail/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
                        
                        val detailViewModel: TaskDetailViewModel = viewModel(
                            factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                    @Suppress("UNCHECKED_CAST")
                                    return TaskDetailViewModel(repository) as T
                                }
                            }
                        )
                        
                        DetailScreen(
                            taskId = taskId,
                            viewModel = detailViewModel,
                            onTaskDeleted = {
                                // Quay về list và reload
                                navController.popBackStack()
                                listViewModel.loadTasks()
                            },
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
