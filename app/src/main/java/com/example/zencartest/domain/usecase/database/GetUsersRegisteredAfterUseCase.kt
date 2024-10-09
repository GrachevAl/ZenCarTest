package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class GetUsersRegisteredAfterUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(currentUserId: String): List<User> {
        return localDatabaseRepository.getUsersRegisteredAfter(currentUserId)
    }
}