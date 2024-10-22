package com.example.zencartest.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Home : Destinations()

    @Serializable
    data object Auth : Destinations()

    @Serializable
    data object Register : Destinations()

    @Serializable
    data class ListDataUsers(
        val login: String,
        val password: String,
    ) : Destinations()
}
