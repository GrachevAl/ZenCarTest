package com.example.zencartest.domain.repository

import com.example.zencartest.utils.TaskResult

interface AuthRepository {

    suspend fun signIn(email: String, password: String): TaskResult<Boolean>
}
