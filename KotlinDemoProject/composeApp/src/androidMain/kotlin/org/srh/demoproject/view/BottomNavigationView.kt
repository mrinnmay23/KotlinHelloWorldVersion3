package org.srh.demoproject.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    navController:NavController,
    selectedScreen:String,
    onItemSelected:(String) ->Unit
){

    BottomNavigation(
        modifier =Modifier.fillMaxWidth(),
        backgroundColor = Color.White
    ) {
        BottomNavigationItem(
            selected =selectedScreen =="Home",
            onClick = {
                onItemSelected("Home")
                navController.navigate("home")
            },
            label ={ Text("Home")},
            icon = {Icon(Icons.Default.Home, contentDescription = "home")}
        )
        BottomNavigationItem(
            selected =selectedScreen =="Login",
            onClick = {
                onItemSelected("Login")
                navController.navigate("login")
            },
            label ={ Text("Login")},
            icon = {Icon(Icons.Default.AccountBox, contentDescription = "login")}
        )
    }

}