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
import com.example.zencartest.presentation.viewmodel.RegistrationViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation<Destinations.Home>(
        startDestination = Destinations.Auth,
    ) {
        composable<Destinations.Auth> {
            LoginScreen {
                navController.navigate(Destinations.Register)
            }
        }

        composable<Destinations.Register> {
            val viewModel = hiltViewModel<RegistrationViewModel>()
            RegistrationScreen(
                viewModel = viewModel,
                onEvent = viewModel::event,
                onNavigate = {
                    navController.navigate(Destinations.ListUsers)
                },
            )
        }
    }
}
