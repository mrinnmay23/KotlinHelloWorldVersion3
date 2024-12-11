package org.srh.demoproject.view

import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController){
    val selectedScreen = remember { mutableStateOf("Login") }

    val username=remember{ mutableStateOf("") }
    val password=remember{ mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ){
        TextField(
            value=username.value,
            onValueChange = {username.value=it},
            label={Text("Enter Username")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value=password.value,
            onValueChange = {password.value=it},
            label={Text("Enter Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("loginSuccess")
            }
        )
        {
            Text("Login")
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(
            navController=navController,
            selectedScreen = selectedScreen.value,
            onItemSelected = {
                    screen -> selectedScreen.value=screen
            }
        )

    }
}