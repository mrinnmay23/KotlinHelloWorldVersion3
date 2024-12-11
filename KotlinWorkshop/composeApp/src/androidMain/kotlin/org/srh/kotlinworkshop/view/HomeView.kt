package org.srh.kotlinworkshop.view

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinworkshop.composeapp.generated.resources.Res
import kotlinworkshop.composeapp.generated.resources.backgroundimg
import kotlinworkshop.composeapp.generated.resources.welcomeimg
import org.jetbrains.compose.resources.painterResource
import org.srh.kotlinworkshop.viewmodel.HomeViewModel

@Composable
fun HomeView(viewModel : HomeViewModel, navController: NavController){

    val context = LocalContext.current
    val urlInput by viewModel.urlInput
    val selectedScreen = remember { mutableStateOf("Home") }

    MaterialTheme {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.backgroundimg),
                contentDescription = "Background image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    "hello",
                    style = TextStyle(fontFamily = FontFamily.Cursive, fontSize = 30.sp, color = Color.Magenta)
                )
                Text(
                    "world",
                    style = TextStyle(fontFamily = FontFamily.Cursive, fontSize = 30.sp, color = Color.Magenta)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(Res.drawable.welcomeimg),
                    contentDescription = "Welcome image",
                    modifier = Modifier.size(150.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = urlInput.inputText,
                    onValueChange = {viewModel.onUrlChange(it)},
                    label = { Text("Enter the Url") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick={
                        if(viewModel.isValidUrl(urlInput.inputText)){
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlInput.inputText))
                            context.startActivity(intent)
                        }else{
                            Toast.makeText(context,"Invalid Url",Toast.LENGTH_SHORT).show()
                        }
                    }
                ){
                    Text("Open Url")
                }

                Spacer(modifier = Modifier.weight(1f))
                BottomNavigationBar(navController=navController,
                    selectedScreen = selectedScreen.value,
                    onItemSelected = {
                        screen -> selectedScreen.value=screen
                    })



            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView(){

    val mockviewModel = HomeViewModel().apply {

    }
    val navController= rememberNavController()
    HomeView(viewModel = mockviewModel, navController)
}
