package com.example.zencartest.presentation.event

sealed class LoginEvent {

    class SignIn(val email: String, val password: String) : LoginEvent()
    data object LogOut : LoginEvent()
    class EmailChanged(val email: String) : LoginEvent()
    class PasswordChanged(val password: String) : LoginEvent()
}
