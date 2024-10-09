package com.example.zencartest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.domain.usecase.auth.SignInUseCase
import com.example.zencartest.domain.usecase.database.GetUserByLoginUseCase
import com.example.zencartest.domain.usecase.prefs.ClearTokenFromStorageUseCase
import com.example.zencartest.domain.usecase.prefs.SaveUserTokenToDataStorageUseCase
import com.example.zencartest.presentation.event.LoginEvent
import com.example.zencartest.presentation.state.SignInState
import com.example.zencartest.utils.TaskResult
import com.example.zencartest.utils.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val logOutUseCase: ClearTokenFromStorageUseCase,
    private val saveUserTokenToDataStorageUseCase: SaveUserTokenToDataStorageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onEvent(loginEvent: LoginEvent, onLoginSuccess: () -> Unit) {
        when (loginEvent) {
            is LoginEvent.SignIn -> {
                signIn(loginEvent.email, loginEvent.password, onLoginSuccess)
            }
            is LoginEvent.LogOut -> {
                logOut()
            }
            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = loginEvent.email) }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = loginEvent.password) }
            }
        }
    }

    private fun signIn(email: String, password: String, onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            when (val result = signInUseCase(email, password)) {
                is TaskResult.Success -> {
                    result.data?.let { token ->
                        saveUserTokenToDataStorageUseCase(token)
                    }
                    _state.update { it.copy(userInDatabase = true) }
                    onLoginSuccess()
                }
                is TaskResult.Error -> {
                    _state.update { it.copy(userInDatabase = false) }
                    SnackbarManager.showMessage(
                        "Пользователь не найден или неверно указаны адрес электронной почты и пароль",
                    )
                }
            }
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            logOutUseCase.invoke()
            _state.update { it.copy(userInDatabase = false) }
        }
    }
}

