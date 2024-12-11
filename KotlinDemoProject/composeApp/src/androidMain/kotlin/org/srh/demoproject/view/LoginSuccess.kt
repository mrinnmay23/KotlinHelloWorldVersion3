package org.srh.demoproject.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun LoginSuccess(){

    val context = LocalContext.current
    var textToSpeak by remember { mutableStateOf(TextFieldValue("")) }


    val tts = remember {
        TextToSpeech(context) { status ->
            if (status != TextToSpeech.SUCCESS) {
                println("TTS Initialization failed")
            }
        }
    }

    LaunchedEffect(Unit) {
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
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Successfully Registered")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Do you wanna say anything?")
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = textToSpeak,
                onValueChange = { textToSpeak = it },
                label = { Text("Enter text to speak") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {
                    val text = textToSpeak.text
                    if (text.isNotEmpty()) {
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Speak", fontSize = 18.sp)
            }
        }
    }

}