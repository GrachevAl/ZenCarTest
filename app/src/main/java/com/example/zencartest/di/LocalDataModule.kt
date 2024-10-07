package com.example.zencartest.di

import android.content.Context
import com.example.zencartest.data.repository.LocalDatabaseRepositoryImpl
import com.example.zencartest.domain.repository.LocalDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

    @Binds
    @Singleton
    abstract fun bindLocalDatabaseRepository(
        localDatabaseRepository: LocalDatabaseRepositoryImpl,
        @ApplicationContext context: Context,
    ): LocalDatabaseRepository
}
