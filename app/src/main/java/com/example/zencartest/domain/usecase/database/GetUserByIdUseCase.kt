package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class GetUserByIdUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(userId: String): User? {
        return localDatabaseRepository.getUserById(userId)
    }
}
