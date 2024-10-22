package com.example.zencartest.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class SignInState(
    val email: String = "",
    val password: String = "",
    val userInDatabase: Boolean = false,
    val token: String? = null,
)
