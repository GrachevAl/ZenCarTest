package com.example.zencartest.domain.usecase.auth

import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.utils.TaskResult

class SignInUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(email: String, password: String): TaskResult<String> {
        val user = localDatabaseRepository.getUserByLogin(email)
        return if (user != null && user.password == password) {
            TaskResult.Success(user.token)
        } else {
            TaskResult.Error("Invalid login or password")
        }
    }
}
