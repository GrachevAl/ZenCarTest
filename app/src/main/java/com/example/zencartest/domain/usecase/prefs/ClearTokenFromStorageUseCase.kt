package com.example.zencartest.domain.usecase.prefs

import com.example.zencartest.domain.repository.PreferencesRepository

class ClearTokenFromStorageUseCase(private val preferencesRepository: PreferencesRepository) {

    suspend operator fun invoke() {
        preferencesRepository.clearUserTokenFromDataStorage()
    }
}
