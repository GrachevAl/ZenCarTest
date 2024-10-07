package com.example.zencartest.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zencartest.domain.model.User
import com.example.zencartest.presentation.screen.ListUsers

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home,
    ) {
        authNavGraph(navController)
        listUsers(navController)
    }
}

fun NavGraphBuilder.listUsers(
    navController: NavHostController,
) {
    composable<Destinations.ListUsers> {
        ListUsers(userList)
    }
}

val userList = listOf(
    User(
        id = "1",
        name = "Alice Johnson",
        birthDate = "12/04/1990",
        photoUrl = "https://example.com/photos/alice.jpg",
        timeAdded = System.currentTimeMillis(),
        token = "token12345"
    ),
    User(
        id = "2",
        name = "Bob Smith",
        birthDate = "23/06/1985",
        photoUrl = "https://example.com/photos/bob.jpg",
        timeAdded = System.currentTimeMillis(),
        token = "token67890"
    ),
    User(
        id = "3",
        name = "Charlie Brown",
        birthDate = "15/09/1992",
        photoUrl = "https://example.com/photos/charlie.jpg",
        timeAdded = System.currentTimeMillis(),
        token = "token09876"
    ),
    User(
        id = "4",
        name = "Diana Prince",
        birthDate = "30/01/1988",
        photoUrl = "https://example.com/photos/diana.jpg",
        timeAdded = System.currentTimeMillis(),
        token = "token54321"
    ),
    User(
        id = "5",
        name = "Evan Turner",
        birthDate = "05/11/1995",
        photoUrl = "https://example.com/photos/evan.jpg",
        timeAdded = System.currentTimeMillis(),
        token = "token11223"
    )
)

