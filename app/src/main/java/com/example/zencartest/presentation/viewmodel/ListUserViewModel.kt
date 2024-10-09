package com.example.zencartest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.domain.usecase.database.DeleteUserByIdUseCase
import com.example.zencartest.domain.usecase.database.GetAllUsersExcludingCurrentUseCase
import com.example.zencartest.domain.usecase.database.GetUserByIdUseCase
import com.example.zencartest.domain.usecase.database.GetUserByLoginUseCase
import com.example.zencartest.domain.usecase.prefs.ClearTokenFromStorageUseCase
import com.example.zencartest.presentation.event.ListUserEvent
import com.example.zencartest.presentation.state.ListUserState
import com.example.zencartest.utils.Resource
import com.example.zencartest.utils.snackbar.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val clearTokenFromStorageUseCase: ClearTokenFromStorageUseCase,
    private val getAllUsersExcludingCurrentUseCase: GetAllUsersExcludingCurrentUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getUserByLoginUseCase: GetUserByLoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ListUserState())
    val state = _state.asStateFlow()

    fun onEvent(listUserEvent: ListUserEvent) {
        when (listUserEvent) {
            is ListUserEvent.LogOut -> {
                logOut()
            }
            is ListUserEvent.ShowUsers -> {
                getAllUsers(listUserEvent.login)
                getCurrentUserByLogin()
            }

            is ListUserEvent.DeleteUserById -> {
                deleteUser(listUserEvent.userId)
            }
        }
    }

    private fun getCurrentUserByLogin() {
        val login = _state.value.name
        viewModelScope.launch {
            val user = getUserByLoginUseCase.invoke(login)
            if (user != null) {
                _state.update {
                    it.copy(
                        timeAdded = user.timeAdded,
                        photoUrl = user.photoUrl,
                    )
                }
            }
        }
    }

    private fun getAllUsers(login: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    name = login,
                )
            }
            getAllUsersExcludingCurrentUseCase.invoke(login).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                listUsers = resource.data,
                            )
                        }
                        Log.d("list", "${resource.data}")
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                listUsers = emptyList(),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun deleteUser(userId: String) {
        getCurrentUserByLogin()
        val currentTimeAdded = _state.value.timeAdded
        viewModelScope.launch {
            val userToDelete = getUserByIdUseCase.invoke(userId)
            if (userToDelete != null && userToDelete.timeAdded > currentTimeAdded) {
                deleteUserByIdUseCase.invoke(userId)
                _state.update {
                    it.copy(
                        listUsers = it.listUsers.filter { user -> user.id != userId },
                    )
                }
            } else {
                SnackbarManager.showMessage("Невозможно удалить пользователя, так как он зарегистрировался раньше")
            }
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            clearTokenFromStorageUseCase.invoke()
            _state.update {
                it.copy(
                    logOut = true,
                )
            }
        }
    }
}
