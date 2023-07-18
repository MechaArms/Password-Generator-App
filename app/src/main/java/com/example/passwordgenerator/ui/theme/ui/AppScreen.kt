package com.example.passwordgenerator.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PasswordGenerator(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel()
){
    val uiState by vm.uiState.collectAsState()
    val n = 8  //password with 8 letters

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (text = "Password Generator")

        Button(onClick = { vm.getRandPassword(n) }) {
            Text (text = "Generate password")
        }

        /* get the password */
        Text(text = uiState.currentPassword)

    }
}