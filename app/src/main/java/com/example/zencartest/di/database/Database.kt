package com.example.zencartest.di.database

import android.content.Context
import androidx.room.Room
import com.example.zencartest.data.database.Database
import com.example.zencartest.utils.NAME_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            NAME_DATABASE,
        )
            .build()
    }
}
