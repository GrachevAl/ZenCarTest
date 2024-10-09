package com.example.zencartest.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.zencartest.presentation.screen.UserListScreen
import com.example.zencartest.presentation.viewmodel.ListUserViewModel
import com.example.zencartest.utils.CustomNavType
import kotlin.reflect.typeOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostRoot(
    navController: NavHostController,
    startDestination: Destinations,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authNavGraph(navController)
        listUsers(navController)
    }
}

fun NavGraphBuilder.listUsers(
    navController: NavHostController,
) {
    composable<Destinations.ListDataUsers>(
        typeMap = mapOf(
            typeOf<Destinations.ListDataUsers>() to CustomNavType.ListDataUsersType,
        ),
    ) { backStackEntry ->
        val viewModel = hiltViewModel<ListUserViewModel>()
        val listDataUsers: Destinations.ListDataUsers = backStackEntry.toRoute()
        UserListScreen(listDataUsers = listDataUsers, onEvent = viewModel::onEvent) {
            navController.navigate(Destinations.Auth) {
                popUpTo(Destinations.Home) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
}
