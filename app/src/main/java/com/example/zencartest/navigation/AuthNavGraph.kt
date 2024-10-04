package com.example.zencartest.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.zencartest.presentation.screen.LoginScreen
import com.example.zencartest.presentation.screen.RegistrationScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    composable<Destinations.Auth> {
        LoginScreen()
    }

    composable<Destinations.Register> {
        RegistrationScreen()
    }
}
