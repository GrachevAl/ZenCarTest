package com.example.zencartest.domain.model

data class User(
    val id: String,
    val name: String,
    val birthDate: String,
    val photoUrl: String?,
    val token: String,
)
