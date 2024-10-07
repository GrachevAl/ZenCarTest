package com.example.zencartest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.domain.usecase.auth.SignInUseCase
import com.example.zencartest.domain.usecase.prefs.ClearTokenFromStorage
import com.example.zencartest.presentation.event.LoginEvent
import com.example.zencartest.presentation.state.SignInState
import com.example.zencartest.utils.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val logOutUseCase: ClearTokenFromStorage,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.SignIn -> {
                signIn(loginEvent.email, loginEvent.password)
            }
            is LoginEvent.LogOut -> {
                logOut()
            }

            is LoginEvent.EmailChanged -> {

            }

            is LoginEvent.PasswordChanged -> {

            }
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            when (val result = signInUseCase(email, password)) {
                is TaskResult.Success -> {
                    _state.update { it.copy(userInDatabase = result.data ?: false) }
                }
                is TaskResult.Error -> {
                    _state.update { it.copy(userInDatabase = false) }
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
