package com.example.zencartest.presentation.event

sealed class ListUserEvent {

    data object LogOut : ListUserEvent()
    class ShowUsers(val login: String) : ListUserEvent()
    class DeleteUserById(val userId: String) : ListUserEvent()
}