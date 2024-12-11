package org.srh.demoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.srh.demoproject.viewmodel.HomeViewModel
import org.srh.demoproject.view.HomeView
import org.srh.demoproject.view.Login
import org.srh.demoproject.view.LoginSuccess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation(){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeView(viewModel = HomeViewModel(),navController)  }
        composable("login"){Login(navController)}
        composable("loginSuccess") { LoginSuccess() }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

