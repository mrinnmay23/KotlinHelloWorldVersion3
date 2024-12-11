package org.srh.helloworld.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*


@Composable
fun RegistrationSuccess() {
//   Column(
//       modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Successfully Registered")
//    }

    val context = LocalContext.current
    var textToSpeak by remember { mutableStateOf(TextFieldValue("")) }

    // Initialize Text-to-Speech
    val tts = remember {
        TextToSpeech(context) { status ->
            if (status != TextToSpeech.SUCCESS) {
                println("TTS Initialization failed")
            }
        }
    }

    // Set language to US English
    LaunchedEffect(Unit) {
        tts.language = Locale.US
    }

    // Clean up TTS when the composable is disposed
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
            // TextField for user input
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

            // Button to trigger Text-to-Speech
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
