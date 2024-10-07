package com.example.zencartest.presentation.state

data class SignUpState(
    val emailOrName: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val birthDate: String = "",
    val imagePath: String? = null,
    val isRegistrationSuccess: Boolean = false,
)
