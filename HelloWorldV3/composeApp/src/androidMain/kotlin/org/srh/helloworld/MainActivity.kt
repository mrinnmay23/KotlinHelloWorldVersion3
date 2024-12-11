package org.srh.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.srh.helloworld.view.HomeView
import org.srh.helloworld.view.Login
import org.srh.helloworld.view.RegistrationSuccess
import org.srh.helloworld.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation()
        }
    }


}

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Create NavController

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeView(viewModel = HomeViewModel(), navController) }
        composable("login") { Login(navController) }
        composable("registrationSuccess") { RegistrationSuccess() }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}