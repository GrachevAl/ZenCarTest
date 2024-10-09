package com.example.zencartest.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.zencartest.presentation.screen.LoginScreen
import com.example.zencartest.presentation.screen.RegistrationScreen
import com.example.zencartest.presentation.viewmodel.LoginViewModel
import com.example.zencartest.presentation.viewmodel.RegistrationViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation<Destinations.Home>(
        startDestination = Destinations.Auth,
    ) {
        composable<Destinations.Auth> {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onEvent = { event ->
                    viewModel.onEvent(event) {
                        // navController.navigate(Destinations.ListUsers)
                    }
                },
                goToPageRegister = { navController.navigate(Destinations.Register) },
                goToPageListUser = { login, password ->
                    navController.navigate(Destinations.ListDataUsers(login, password))
                },
            )
        }

        composable<Destinations.Register> {
            val viewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(
                viewModel = viewModel,
                onEvent = viewModel::event,
                onNavigate = { login, password ->
                    navController.navigate(Destinations.ListDataUsers(login, password))
                },
            )
        }
    }
}
