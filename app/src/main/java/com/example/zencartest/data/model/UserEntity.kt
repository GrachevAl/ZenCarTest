package com.example.zencartest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zencartest.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val birthDate: String,
    val photoUrl: String?,
    val token: String,
)
