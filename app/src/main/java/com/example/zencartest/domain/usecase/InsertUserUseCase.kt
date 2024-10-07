package com.example.zencartest.domain.usecase

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class InsertUserUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(user: User){
        localDatabaseRepository.insertUser(user)
    }
}