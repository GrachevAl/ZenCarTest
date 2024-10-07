package com.example.zencartest.data.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zencartest.data.model.UserEntity

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM USERS WHERE id = :id")
    suspend fun deleteUser(id: String)

    @Query("SELECT * FROM USERS ORDER BY timeAdded DESC")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM USERS WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?
}
