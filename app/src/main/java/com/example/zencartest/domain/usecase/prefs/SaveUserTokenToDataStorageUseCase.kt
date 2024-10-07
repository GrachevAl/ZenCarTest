package com.example.zencartest.domain.usecase.prefs

import com.example.zencartest.domain.repository.PreferencesRepository

class SaveUserTokenToDataStorageUseCase(private val preferencesRepository: PreferencesRepository) {
    suspend operator fun invoke(userToken: String) {
        preferencesRepository.saveUserTokenToDataStorage(userToken)
    }
}
