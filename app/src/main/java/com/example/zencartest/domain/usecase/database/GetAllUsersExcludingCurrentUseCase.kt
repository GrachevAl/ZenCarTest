package com.example.zencartest.domain.usecase.database

import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllUsersExcludingCurrentUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {

    suspend operator fun invoke(login: String): Flow<Resource<List<User>>> {
        return localDatabaseRepository.getAllUsersExcludingCurrent(login)
    }
}