package com.example.zencartest.di.auth

import com.example.zencartest.data.database.Database
import com.example.zencartest.data.repository.AuthRepositoryImpl
import com.example.zencartest.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(database: Database,): AuthRepository{
        return AuthRepositoryImpl(database)
    }
}