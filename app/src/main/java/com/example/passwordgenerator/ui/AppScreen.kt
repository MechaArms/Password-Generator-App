package com.example.passwordgenerator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordgenerator.R
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
            style = MaterialTheme.typography.titleLarge
        )

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        ){
            Text(
                text = "Size: ${sliderPosition.toInt()}",
                modifier = Modifier
                    .padding(
                        top =dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
            )
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it.roundToInt().toFloat() },
                valueRange = 5f..25f
            )
        }

        Button(onClick = { generatePassword(sliderPosition.roundToInt()) }) {
            Text (
                text = "Generate password",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        /* get the password */
        Text(
            text = showPassword,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = numberLetters,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
