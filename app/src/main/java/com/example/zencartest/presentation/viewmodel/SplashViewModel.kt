package com.example.zencartest.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zencartest.domain.usecase.database.GetUserByTokenUseCase
import com.example.zencartest.domain.usecase.prefs.GetUserTokenFromDataStorageUseCase
import com.example.zencartest.presentation.state.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserTokenFromDataStorageUseCase: GetUserTokenFromDataStorageUseCase,
    private val getUserByTokenUseCase: GetUserByTokenUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getUserTokenAndUser()
        }
    }

    private suspend fun getUserTokenAndUser() {
        getUserTokenFromDataStorageUseCase().collect { token ->
            if (!token.isNullOrEmpty()) {
                getUserByToken(token)
            } else {
                _state.value = SplashScreenState(isLoggedIn = false, isLoading = false)
            }
        }
    }

    private suspend fun getUserByToken(token: String) {
        val user = getUserByTokenUseCase(token)
        if (user != null) {
            _state.value = SplashScreenState(
                isLoggedIn = true,
                login = user.name,
                password = user.password,
                isLoading = false,
            )
        } else {
            _state.value = SplashScreenState(isLoggedIn = false, isLoading = false)
        }
    }
}
