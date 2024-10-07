package com.example.zencartest.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.R
import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.usecase.InsertUserUseCase
import com.example.zencartest.domain.usecase.SaveImageToPrivateStorageUseCase
import com.example.zencartest.presentation.event.RegistrationEvent
import com.example.zencartest.presentation.state.SignUpState
import com.example.zencartest.utils.generateImageNameForStorage
import com.example.zencartest.utils.generateRandomToken
import com.example.zencartest.utils.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val saveImageToPrivateStorageUseCase: SaveImageToPrivateStorageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun event(registrationEvent: RegistrationEvent) {
        when (registrationEvent) {
            is RegistrationEvent.RegistrationUser -> {
                registrationUser()
            }
            is RegistrationEvent.SetNameUser -> {
                _state.update {
                    it.copy(
                        emailOrName = registrationEvent.nameUser,
                    )
                }
            }
            is RegistrationEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = registrationEvent.password,
                    )
                }
            }
            is RegistrationEvent.SetBirthDate -> {
                _state.update {
                    it.copy(
                        birthDate = registrationEvent.birthDate,
                    )
                }
            }
            is RegistrationEvent.SetPathToImage -> {
                _state.update {
                    it.copy(
                        imagePath = registrationEvent.pathToImage.toString(),
                    )
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrationUser(){
        val name = state.value.emailOrName
        val password = state.value.password
        val birthDate = state.value.birthDate
        val pathToImage = state.value.imagePath
        val token = generateRandomToken()
        val generationName = generateImageNameForStorage()
        if (name.isBlank() || password.isNullOrBlank() || pathToImage.isNullOrBlank()
        ) {
            SnackbarManager.showMessage(R.string.notallfields)
            return
        }


        val user = User(
            name = name,
            birthDate = birthDate,
        )
        viewModelScope.launch {
            saveImageToPrivateStorageUseCase.invoke(pathToImage.toUri(), generationName)
            insertUserUseCase.invoke(user)
            _state.update {
                it.copy(
                    emailOrName = "",
                    password = "",
                    imagePath = "",
                )
            }
        }
    }
}
