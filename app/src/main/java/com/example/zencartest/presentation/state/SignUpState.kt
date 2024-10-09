package com.example.zencartest.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class SignUpState(
    val emailOrName: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val birthDate: String = "",
    val imagePath: String? = null,
    val isRegistrationSuccess: Boolean = false,
)
