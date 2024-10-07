package com.example.zencartest.domain.model

import com.example.zencartest.utils.UNDEFINED_ID

data class User(
    val id: String = UNDEFINED_ID,
    val name: String = "",
    val birthDate: String = "",
    val photoUrl: String? = "",
    val timeAdded: Long = 0L,
    val token: String = "",
)
