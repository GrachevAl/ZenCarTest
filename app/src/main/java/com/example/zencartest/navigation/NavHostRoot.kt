package com.example.zencartest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zencartest.presentation.screen.ListUsers

@Composable
fun NavHostRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Auth,
    ) {
        authNavGraph(navController)
        listUsers(navController)
    }
}

fun NavGraphBuilder.listUsers(
    navController: NavHostController,
) {
    composable<Destinations.ListUsers> {
        //ListUsers()
    }
}

// Добавить еще экраны если нужно
