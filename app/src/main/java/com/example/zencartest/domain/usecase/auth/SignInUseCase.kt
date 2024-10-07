package com.example.zencartest.domain.usecase.auth

import com.example.zencartest.domain.repository.AuthRepository
import com.example.zencartest.utils.TaskResult

class SignInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): TaskResult<Boolean> {
        return authRepository.signIn(email, password)
    }
}