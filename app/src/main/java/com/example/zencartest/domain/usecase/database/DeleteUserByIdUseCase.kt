package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.repository.LocalDatabaseRepository
import javax.inject.Inject

class DeleteUserByIdUseCase @Inject constructor(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(id: String) {
        localDatabaseRepository.deleteUser(id)
    }
}