package com.example.passwordgenerator.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt

@Composable
fun ScreenPasswordApp(vm: MainViewModel = viewModel()){
    val uiState by vm.uiState.collectAsState()
    PasswordGenerator(
        numberLetters = uiState.numberOfLetters,
        generatePassword = { vm.getRandPassword(it) },
        showPassword = uiState.currentPassword
    )
}

@Composable
fun PasswordGenerator(
    numberLetters: String,
    generatePassword: (Int) -> Unit,
    showPassword: String
){
    var sliderPosition by rememberSaveable { mutableStateOf(5f) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text (
            text = "Password Generator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(24.dp))

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it.roundToInt().toFloat() },
            valueRange = 5f..30f
        )

        Text(text = "Size: ${sliderPosition.toInt()}")

        Spacer(modifier = Modifier.padding(24.dp))

        Button(onClick = { generatePassword(sliderPosition.roundToInt()) }) {
            Text (text = "Generate password")
        }

        Spacer(modifier = Modifier.padding(24.dp))

        /* get the password */
        Text(text = showPassword)

        Spacer(modifier = Modifier.padding(24.dp))

        Text(text = numberLetters)
    }
}