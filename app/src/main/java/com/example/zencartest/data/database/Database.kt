package com.example.zencartest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zencartest.data.database.local.Dao
import com.example.zencartest.data.model.UserEntity

@Database(
    version = 1,
    entities = [UserEntity::class],
)
abstract class Database : RoomDatabase(){

    abstract fun repositoryDao(): Dao
}
