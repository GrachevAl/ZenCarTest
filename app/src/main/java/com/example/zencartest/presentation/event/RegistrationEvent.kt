package com.example.zencartest.presentation.event

import android.net.Uri

sealed class RegistrationEvent {
    data object RegistrationUser : RegistrationEvent()
    class SetNameUser(val nameUser: String) : RegistrationEvent()
    class SetPassword(val password: String) : RegistrationEvent()
    class SetBirthDate(val birthDate: String) : RegistrationEvent()
    class SetPathToImage(val pathToImage: Uri) : RegistrationEvent()
    class IsRegistrationSuccess(val isSuccess: Boolean) : RegistrationEvent()
}
