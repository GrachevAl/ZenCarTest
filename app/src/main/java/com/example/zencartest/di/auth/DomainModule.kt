package com.example.zencartest.di.auth

import com.example.zencartest.domain.repository.AuthRepository
import com.example.zencartest.domain.repository.LocalDatabaseRepository
import com.example.zencartest.domain.usecase.InsertUserUseCase
import com.example.zencartest.domain.usecase.auth.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository = authRepository)
    }

}