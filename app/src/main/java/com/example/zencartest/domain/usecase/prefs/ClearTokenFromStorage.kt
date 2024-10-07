package com.example.zencartest.domain.usecase.prefs

import com.example.zencartest.domain.repository.PreferencesRepository

class ClearTokenFromStorage(private val preferencesRepository: PreferencesRepository) {

    suspend operator fun invoke(){
        preferencesRepository.clearUserTokenFromDataStorage()
    }
}