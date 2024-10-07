package com.example.zencartest.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.zencartest.domain.repository.LocalDatabaseRepository

class SaveImageToPrivateStorageUseCase(private val localDatabaseRepository: LocalDatabaseRepository) {
    suspend operator fun invoke(uri: Uri, nameOfImage: String): String {
        return localDatabaseRepository.saveImageToPrivateStorage(uri, nameOfImage)
    }
}