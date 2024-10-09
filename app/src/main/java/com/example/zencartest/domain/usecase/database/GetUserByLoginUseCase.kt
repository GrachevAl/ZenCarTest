package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class GetUserByLoginUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(login: String): User? {
        return localDatabaseRepository.getUserByLogin(login)
    }
}
