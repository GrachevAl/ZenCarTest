package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class GetUserByTokenUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(token: String): User? {
        return localDatabaseRepository.getUserByToken(token)
    }
}
