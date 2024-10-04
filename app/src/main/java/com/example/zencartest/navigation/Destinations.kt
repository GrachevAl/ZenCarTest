package com.example.zencartest.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    data object Auth : Destinations()

    @Serializable
    data object Register : Destinations()

    @Serializable
    data object ListUsers : Destinations()
}
