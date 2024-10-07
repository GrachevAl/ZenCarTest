package com.example.zencartest.domain.usecase.prefs

import com.example.zencartest.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetUserTokenFromDataStorageUseCase(private val preferencesRepository: PreferencesRepository) {

    suspend operator fun invoke(): Flow<String?> {
        return preferencesRepository.getUserTokenFromDataStorage()
    }
}