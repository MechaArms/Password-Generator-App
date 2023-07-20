package com.example.passwordgenerator.ui
import kotlin.random.Random
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    // Game UI state
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun getRandPassword(n: Int) {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~@#$%^&*()!"

        val random = Random(System.nanoTime())
        val password = StringBuilder()

        for (i in 0 until n)
        {
            val rIndex = random.nextInt(characterSet.length)
            password.append(characterSet[rIndex])
        }
        updateUiState(password.toString(),n.toString())   // Exemple: UFJr7t@w
    }

    private fun updateUiState(updatedPassword: String, updateSizePassword:String) {
        val updateWordSize = "\nThis password have a $updateSizePassword letters,\n ${isSecurity(updateSizePassword.toInt())}"

        _uiState.update { currentState ->
            currentState.copy(
                currentPassword = updatedPassword,
                numberOfLetters = updateWordSize
            )
        }
    }

    private fun isSecurity(isSafe: Int): String {
        val text: String
        when(isSafe){
            in 0..7 -> text = "is a weak password"
            in 8..12 -> text = "is a good password"
            in 13..16 -> text = "is a strong password"
            else -> text = "is a very strong password"
        }
        return text
    }
}