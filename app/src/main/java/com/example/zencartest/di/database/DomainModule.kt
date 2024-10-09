package com.example.zencartest.di.database

import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.domain.usecase.database.DeleteUserByIdUseCase
import com.example.zencartest.domain.usecase.database.GetAllUsersUseCase
import com.example.zencartest.domain.usecase.database.GetUserByIdUseCase
import com.example.zencartest.domain.usecase.database.CheckUserExistsUseCase
import com.example.zencartest.domain.usecase.database.GetAllUsersExcludingCurrentUseCase
import com.example.zencartest.domain.usecase.database.GetUserByLoginUseCase
import com.example.zencartest.domain.usecase.database.GetUserByTokenUseCase
import com.example.zencartest.domain.usecase.database.GetUsersRegisteredAfterUseCase
import com.example.zencartest.domain.usecase.database.InsertUserUseCase
import com.example.zencartest.domain.usecase.database.SaveImageToPrivateStorageUseCase
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
    fun provideDeleteUserUseCase(localDatabaseRepository: LocalDatabaseRepository): DeleteUserByIdUseCase {
        return DeleteUserByIdUseCase(localDatabaseRepository = localDatabaseRepository)
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

    @Provides
    fun provideGetUserByLoginAndPasswordUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): CheckUserExistsUseCase {
        return CheckUserExistsUseCase(localDatabaseRepository = localDatabaseRepository)
    }
    @Provides
    fun provideGetUserByLoginUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): GetUserByLoginUseCase {
        return GetUserByLoginUseCase(localDatabaseRepository = localDatabaseRepository)
    }
    @Provides
    fun provideGetAllUsersExcludingCurrentUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): GetAllUsersExcludingCurrentUseCase {
        return GetAllUsersExcludingCurrentUseCase(localDatabaseRepository = localDatabaseRepository)
    }
    @Provides
    fun provideGetUserByTokenUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): GetUserByTokenUseCase {
        return GetUserByTokenUseCase(localDatabaseRepository = localDatabaseRepository)
    }
    @Provides
    fun provideGetUsersRegisteredAfterUseCase(
        localDatabaseRepository: LocalDatabaseRepository,
    ): GetUsersRegisteredAfterUseCase {
        return GetUsersRegisteredAfterUseCase(localDatabaseRepository = localDatabaseRepository)
    }
}
