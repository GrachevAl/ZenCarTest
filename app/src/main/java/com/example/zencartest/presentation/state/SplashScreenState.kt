package com.example.zencartest.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class SplashScreenState(
    val isLoggedIn: Boolean = false,
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = true,
)
