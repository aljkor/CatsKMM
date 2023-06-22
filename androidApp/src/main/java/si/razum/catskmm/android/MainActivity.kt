package si.razum.catskmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import si.razum.catskmm.android.ui.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import si.razum.catskmm.android.ui.details.DetailsScreen
import si.razum.catskmm.android.ui.home.HomeScreen
import si.razum.catskmm.android.utils.Constants.Companion.BREED_DETAIL_SCREEN
import si.razum.catskmm.android.utils.Constants.Companion.HOME_SCREEN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = HOME_SCREEN) {
                        composable(HOME_SCREEN) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            BREED_DETAIL_SCREEN,
                            arguments = listOf(navArgument("breedId") {
                                type = NavType.StringType
                            })
                        ) {
                            DetailsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
