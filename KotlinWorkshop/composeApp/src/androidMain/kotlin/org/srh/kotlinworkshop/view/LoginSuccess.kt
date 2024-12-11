package org.srh.kotlinworkshop.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment

import android.speech.tts.TextToSpeech

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun LoginSuccess(){
    val context = LocalContext.current
    var textToSpeak by remember{ mutableStateOf(TextFieldValue("")) }

    val tts = remember {
        TextToSpeech(context){
            status ->
            if(status != TextToSpeech.SUCCESS){
                println("TTS failed")
            }
        }
    }

    LaunchedEffect(Unit){
        tts.language = Locale.US
    }

    DisposableEffect(Unit) {
        onDispose {
            tts.shutdown()
        }
    }

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){

            Text(
                "Login Success",
                style = TextStyle(fontFamily = FontFamily.Serif, fontSize = 30.sp, color = Color.Magenta)
            )


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Do you wanna say something?",
                style = TextStyle(fontFamily = FontFamily.Serif, fontSize = 30.sp, color = Color.Magenta)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = textToSpeak,
                onValueChange = {textToSpeak = it},
                label = { Text("Enter text") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    val text =textToSpeak.text
                    if(text.isNotEmpty()){
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                }
            ){
                Text("Speak")
            }

        }
    }
}