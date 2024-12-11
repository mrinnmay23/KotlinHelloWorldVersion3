package org.srh.helloworld.view

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import helloworldv3.composeapp.generated.resources.Res
import helloworldv3.composeapp.generated.resources.background
import helloworldv3.composeapp.generated.resources.welcome
import org.jetbrains.compose.resources.painterResource
import org.srh.helloworld.viewmodel.HomeViewModel


@Composable
fun HomeView(viewModel : HomeViewModel, navController: NavHostController) {


    val selectedScreen = remember{ mutableStateOf("Home")}
    val context= LocalContext.current
    val urlInput by viewModel.urlInput
    MaterialTheme {

        // Use a Box to layer background and text
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image
            Image(
                painter = painterResource(Res.drawable.background),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(), // Ensures image fills the entire screen
                contentScale = ContentScale.Crop // Crops the image to fill the screen
            )

            // Text overlay
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Vertically center the text
            ) {
                Text("Hello", color = Color.White, style = MaterialTheme.typography.h4)
                Text("World", color = Color.White, style = MaterialTheme.typography.h4)

                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(Res.drawable.welcome),
                    contentDescription = "Welcome image",
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = urlInput.inputText,
                    onValueChange = {viewModel.onUrlChange(it)},
                    label = { Text("Enter the URL") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black, // Text color inside the field
                        backgroundColor = Color.White, // Transparent background for TextField (or change to white if preferred)
                        focusedIndicatorColor = Color.White, // White indicator line when focused
                        unfocusedIndicatorColor = Color.White // White indicator line when not focused
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if(viewModel.isValidUrl(urlInput.inputText)){
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlInput.inputText))
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                        }
                    }
                ){
                    Text("Open URL") // Add content to the Button
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        navController.navigate("login") // Navigate to the Login screen
                    }

                ){
                    Text("login")
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {

    val mockViewModel = HomeViewModel().apply {

    }
    val navController = rememberNavController()

    HomeView(viewModel = mockViewModel, navController = navController)
}
