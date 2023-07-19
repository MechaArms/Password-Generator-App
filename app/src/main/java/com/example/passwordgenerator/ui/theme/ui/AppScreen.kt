package com.example.passwordgenerator.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

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
    numberLetters: Int,
    generatePassword: (Int) -> Unit,
    showPassword: String
){

    val n = 8  //password with 8 letters


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text (
            text = "Password Generator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Button(onClick = { generatePassword(n) }) {
            Text (text = "Generate password")
        }

        /* get the password */
        Text(text = showPassword)

    }
}
