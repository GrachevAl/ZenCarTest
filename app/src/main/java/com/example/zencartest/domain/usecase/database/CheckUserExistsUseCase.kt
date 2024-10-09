package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.repository.LocalDatabaseRepository

class CheckUserExistsUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(login: String): Boolean {
        return localDatabaseRepository.checkUserExists(login)
    }
}
