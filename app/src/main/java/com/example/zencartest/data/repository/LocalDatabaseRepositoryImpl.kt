package com.example.zencartest.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import com.example.zencartest.data.database.Database
import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.utils.Resource
import com.example.zencartest.utils.toUser
import com.example.zencartest.utils.toUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class LocalDatabaseRepositoryImpl @Inject constructor(
    private val database: Database,
    private val context: Context,
) : LocalDatabaseRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insertUser(user: User) {
        database.repositoryDao().insertUser(user.toUserEntity())
    }

    override suspend fun deleteUser(id: String) {
        database.repositoryDao().deleteUser(id)
    }

    override suspend fun getAllUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading)
        try {
            val result = database.repositoryDao().getAllUsers()
            if (result.isNotEmpty()) {
                val userList = result.map { it.toUser() }
                emit(Resource.Success(userList))
            } else {
                emit(Resource.Error(404, "No users found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(500, e.message ?: "Unknown error occurred"))
        }
    }

    suspend fun getAllUsersExcludingCurrent(currentUserId: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading)
        try {
            val result = database.repositoryDao().getAllUsers() // Получаем всех пользователей
            val filteredUsers = result.filter { it.id != currentUserId } // Фильтруем текущего пользователя
            if (filteredUsers.isNotEmpty()) {
                val userList = filteredUsers.map { it.toUser() }
                emit(Resource.Success(userList))
            } else {
                emit(Resource.Error(404, "No users found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(500, e.message ?: "Unknown error occurred"))
        }
    }

    override suspend fun getUserById(userId: String): User? {
        return database.repositoryDao().getUserById(userId)?.toUser()
    }

    override suspend fun saveImageToPrivateStorage(uri: Uri, nameOfImage: String): String {
        val filePath =
            File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "my_album")
        if (!filePath.exists()) {
            filePath.mkdirs()
        }
        val file = File(filePath, nameOfImage)
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        BitmapFactory
            .decodeStream(inputStream)
            .compress(Bitmap.CompressFormat.JPEG, 100, outputStream)

        return file.absolutePath
    }
}
