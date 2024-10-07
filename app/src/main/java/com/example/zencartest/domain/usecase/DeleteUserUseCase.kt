package com.example.zencartest.domain.usecase

import com.example.zencartest.domain.repository.LocalDatabaseRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(id: String) {
        localDatabaseRepository.deleteUser(id)
    }
}