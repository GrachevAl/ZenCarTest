package com.example.zencartest.domain.repository

import android.net.Uri
import com.example.zencartest.data.model.UserEntity
import com.example.zencartest.domain.model.User
import com.example.zencartest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseRepository {
    suspend fun insertUser(user: User)

    suspend fun deleteUser(id: String)

    suspend fun getAllUsers(): Flow<Resource<List<User>>>

    suspend fun getAllUsersExcludingCurrent(login: String): Flow<Resource<List<User>>>

    suspend fun checkUserExists(login: String): Boolean

    suspend fun getUserById(userId: String): User?

    suspend fun getUserByLogin(login: String): User?

    suspend fun saveImageToPrivateStorage(uri: Uri, nameOfImage: String): String

    suspend fun getUserByToken(token: String): User?

    suspend fun getUsersRegisteredAfter(currentUserId: String): List<User>
}
