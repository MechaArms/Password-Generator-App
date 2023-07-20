package com.example.passwordgenerator.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordgenerator.R
import com.example.passwordgenerator.ui.theme.PasswordGeneratorTheme
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
//        verticalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.padding_extra_large),
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_large)),
            ) {
        Text (
            text = "Password Generator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it.roundToInt().toFloat() },
            valueRange = 5f..30f
        )

        Text(text = "Password Size: ${sliderPosition.toInt()}")

        Button(onClick = { generatePassword(sliderPosition.roundToInt()) }) {
            Text (text = "Generate password")
        }

        /* get the password */
        Text(text = showPassword)

        Text(text = numberLetters)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PasswordGeneratorTheme {
        ScreenPasswordApp()
    }
}