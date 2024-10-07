package com.example.zencartest.presentation.state

data class SignInState(
    val email: String = "",
    val password: String = "",
    val userInDatabase: Boolean = false,
)