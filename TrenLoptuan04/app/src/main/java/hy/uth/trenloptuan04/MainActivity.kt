package hy.uth.trenloptuan04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hy.uth.trenloptuan04.ui.theme.TrenLoptuan04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrenLoptuan04Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "root") {
        composable("root") { RootScreen(navController) }
        composable("list") { ListScreen(navController) }
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(navController, itemId)
        }
    }
}

@Composable
fun RootScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpackcomposelogo),
                contentDescription = "Jetpack Compose Logo"
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text("Root Screen")
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { navController.navigate("list") }) {
                Text("Push")
            }
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    val items = (1..20).map { "Item #$it" }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text("LazyColumn List")
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/$item") }
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun DetailScreen(navController: NavController, itemId: String?) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Detail Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Displaying details for: $itemId")
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("list") {
                popUpTo("root")
            } }) {
                Text("Detail -> List -> Root")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack("root", inclusive = false) }) {
                Text("Detail -> Root")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrenLoptuan04Theme {
        MyApp()
    }
}
