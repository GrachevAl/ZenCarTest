package com.example.zencartest.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.R
import com.example.zencartest.domain.model.User
import com.example.zencartest.domain.usecase.database.CheckUserExistsUseCase
import com.example.zencartest.domain.usecase.database.InsertUserUseCase
import com.example.zencartest.domain.usecase.database.SaveImageToPrivateStorageUseCase
import com.example.zencartest.domain.usecase.prefs.SaveUserTokenToDataStorageUseCase
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
    private val saveUserTokenToDataStorageUseCase: SaveUserTokenToDataStorageUseCase,
    private val checkUserExistsUseCase: CheckUserExistsUseCase,
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
                Log.d("Image", "${_state.value.imagePath}")
            }

            is RegistrationEvent.IsRegistrationSuccess -> {
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrationUser() {
        val name = _state.value.emailOrName
        val password = _state.value.password
        val birthDate = _state.value.birthDate
        val pathToImage = _state.value.imagePath
        val generationName = generateImageNameForStorage()
        val generationToken = generateRandomToken()

        if (name.isBlank() || password.isNullOrBlank() || pathToImage.isNullOrBlank()) {
            SnackbarManager.showMessage(R.string.notallfields)
            return
        }

        viewModelScope.launch {
            val userExists = checkUserExistsUseCase.invoke(name)
            if (userExists) {
                SnackbarManager.showMessage("Пользователь уже существует")
                return@launch
            }

            val user = User(
                name = name,
                password = password,
                birthDate = birthDate,
                photoUrl = generationName,
                token = generationToken,
            )

            saveImageToPrivateStorageUseCase.invoke(pathToImage.toUri(), generationName)
            insertUserUseCase.invoke(user)
            saveUserTokenToDataStorageUseCase.invoke(generationToken)

            _state.update {
                it.copy(isRegistrationSuccess = true)
            }
        }
    }
}
