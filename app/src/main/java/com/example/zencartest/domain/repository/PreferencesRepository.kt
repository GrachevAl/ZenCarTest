package com.example.zencartest.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun saveUserTokenToDataStorage(userToken: String)

    suspend fun getUserTokenFromDataStorage(): Flow<String?>

    suspend fun clearUserTokenFromDataStorage()
}