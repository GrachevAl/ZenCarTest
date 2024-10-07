package com.example.zencartest.data.repository

import com.example.zencartest.data.database.Database
import com.example.zencartest.domain.repository.AuthRepository
import com.example.zencartest.utils.TaskResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val database: Database,
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): TaskResult<Boolean> {
        return try {
            val user = database.repositoryDao().getAllUsers().find { it.name == email && it.password == password }
            if (user != null) {
                TaskResult.Success(true)
            } else {
                TaskResult.Error("User not found or password is incorrect")
            }
        } catch (e: Exception) {
            TaskResult.Error(e.message ?: "Unknown error")
        }
    }
}
