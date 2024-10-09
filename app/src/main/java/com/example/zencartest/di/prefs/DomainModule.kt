package com.example.zencartest.di.prefs

import com.example.zencartest.domain.repository.PreferencesRepository
import com.example.zencartest.domain.usecase.prefs.ClearTokenFromStorageUseCase
import com.example.zencartest.domain.usecase.prefs.GetUserTokenFromDataStorageUseCase
import com.example.zencartest.domain.usecase.prefs.SaveUserTokenToDataStorageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetLocationIdFromDataStorageUseCase(
        preferencesRepository: PreferencesRepository,
    ): GetUserTokenFromDataStorageUseCase {
        return GetUserTokenFromDataStorageUseCase(preferencesRepository)
    }

    @Provides
    fun provideSaveLocationIdToDataStorageUseCase(
        preferencesRepository: PreferencesRepository,
    ): SaveUserTokenToDataStorageUseCase {
        return SaveUserTokenToDataStorageUseCase(preferencesRepository)
    }

    @Provides
    fun provideClearTokenFromStorageUseCase(
        preferencesRepository: PreferencesRepository,
    ): ClearTokenFromStorageUseCase {
        return ClearTokenFromStorageUseCase(preferencesRepository)
    }
}
