package com.example.zencartest.di.database

import android.content.Context
import com.example.zencartest.data.database.Database
import com.example.zencartest.data.repository.LocalDatabaseRepositoryImpl
import com.example.zencartest.domain.repository.LocalDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDatabaseRepository(
        database: Database,
        @ApplicationContext context: Context,
    ): LocalDatabaseRepository {
        return LocalDatabaseRepositoryImpl(database, context)
    }
}
