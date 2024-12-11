package org.srh.kotlinworkshop.view

import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
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

    val username= remember { mutableStateOf("") }
    val password= remember { mutableStateOf("") }
    val selectedScreen = remember { mutableStateOf("Login") }
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            isGranted ->
            if(isGranted){
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(cameraIntent)
            } else{
                Toast.makeText(context,"Permission Denied", Toast.LENGTH_SHORT).show()

            }
        }
    )


    Column(

    ){

        TextField(
            value = username.value,
            onValueChange = {username.value=it},
            label = { Text("Enter Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password.value,
            onValueChange = {password.value=it},
            label = { Text("Enter Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to open the camera
        Button(
            onClick = {
                // Request camera permission
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        ) {
            Text("Open Camera")
        }

        Button(
            onClick = {
                navController.navigate("loginSuccess")
            }
        ){
            Text("Login")
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(navController=navController,
            selectedScreen = selectedScreen.value,
            onItemSelected = {
                    screen -> selectedScreen.value=screen
            })



    }


}