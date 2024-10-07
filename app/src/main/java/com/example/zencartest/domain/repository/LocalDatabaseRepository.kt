package com.example.zencartest.domain.repository

import android.content.Context
import android.net.Uri
import com.example.zencartest.domain.model.User
import com.example.zencartest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseRepository {
    suspend fun insertUser(user: User)

    suspend fun deleteUser(id: String)

    suspend fun getAllUsers(): Flow<Resource<List<User>>>

    suspend fun getUserById(userId: String): User?

    suspend fun saveImageToPrivateStorage(uri: Uri, nameOfImage: String): String
}
