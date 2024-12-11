package org.srh.helloworld.view

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
fun Login(navController: NavController) {

    val selectedScreen = remember{ mutableStateOf("Home")}
    // State variables to hold the text input for username and password
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Context to start activities
    val context = LocalContext.current

    // Launcher for requesting camera permission
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permission granted, open the camera
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(cameraIntent)
            } else {
                Log.d("Login", "Camera permission denied")
            }
        }
    )

    // Launcher for starting the camera
    val startCameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { image ->
            // Handle the captured image here (e.g., display it or save it)
            if (image != null) {
                Log.d("Login", "Image captured successfully")
            } else {
                Log.d("Login", "Image capture failed or canceled")
            }
        }
    )

    // Use Column to arrange the TextFields vertically
    Column(
        modifier = Modifier.padding(16.dp) // Optional padding around the entire column
    ) {
        // Text field for username
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Enter username") },
            modifier = Modifier.fillMaxWidth() // Ensure the TextField takes up full width
        )

        // Add space between the username and password fields
        Spacer(modifier = Modifier.height(16.dp))

        // Text field for password
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(), // Hides password text
            modifier = Modifier.fillMaxWidth() // Ensure the TextField takes up full width
        )

        // Add space between the password field and the button
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
        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate to the registration success page
        Button(
            onClick = {
                navController.navigate("registrationSuccess") // Navigate to the Registration Success page
            }
        ) {
            Text("Register")
        }


        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(
            navController =navController,
            selectedScreen = selectedScreen.value,
            onItemSelected = {
                    screen ->
                selectedScreen.value=screen
            }
        )
    }
}
