package com.example.zencartest.di

import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.domain.usecase.DeleteUserUseCase
import com.example.zencartest.domain.usecase.GetAllUsersUseCase
import com.example.zencartest.domain.usecase.GetUserByIdUseCase
import com.example.zencartest.domain.usecase.InsertUserUseCase
import com.example.zencartest.domain.usecase.SaveImageToPrivateStorageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideInsertUserUseCase(localDatabaseRepository: LocalDatabaseRepository): InsertUserUseCase {
        return InsertUserUseCase(localDatabaseRepository = localDatabaseRepository)
    }

    @Provides
    fun provideDeleteUserUseCase(localDatabaseRepository: LocalDatabaseRepository): DeleteUserUseCase {
        return DeleteUserUseCase(localDatabaseRepository = localDatabaseRepository)
    }

    @Provides
    fun provideGetAllUsersUseCase(localDatabaseRepository: LocalDatabaseRepository): GetAllUsersUseCase {
        return GetAllUsersUseCase(localDatabaseRepository = localDatabaseRepository)
    }

    @Provides
    fun provideGetUserByIdUseCase(localDatabaseRepository: LocalDatabaseRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(localDatabaseRepository = localDatabaseRepository)
    }

    @Provides
    fun provideSaveImageToStorageUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): SaveImageToPrivateStorageUseCase {
        return SaveImageToPrivateStorageUseCase(localDatabaseRepository = localDatabaseRepository)
    }
}
