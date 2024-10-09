package com.example.zencartest.presentation.state

import androidx.compose.runtime.Stable
import com.example.zencartest.domain.model.User
@Stable
data class ListUserState(
    val name: String = "",
    val birthDate: String = "",
    val photoUrl: String? = "",
    val timeAdded: Long = 0L,
    val logOut: Boolean = false,
    val listUsers: List<User> = emptyList(),
)
