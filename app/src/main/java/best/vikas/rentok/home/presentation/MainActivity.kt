package best.vikas.rentok.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import best.vikas.rentok.home.domain.MainViewModel
import best.vikas.rentok.ui.theme.RentOkTheme
import best.vikas.rentok.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentOkTheme {
                val navController = rememberNavController()
                BaseAppScreenHost(
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BaseAppScreenHost(
    navController: NavHostController,
) {
    val mainViewModel = hiltViewModel<MainViewModel>()

    NavHost(navController = navController, startDestination = Screen.UserList.route) {
        composable(route = Screen.UserList.route) {
            UserListScreenUI(mainViewModel = mainViewModel) { userName ->
                navController.navigate(Screen.UserDetail.route + "/$userName")
            }
        }

        composable(
            route = Screen.UserDetail.route + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("username")
            userName?.let { UserDetailsScreen(userName = it, mainViewModel = mainViewModel) }
        }
    }
}