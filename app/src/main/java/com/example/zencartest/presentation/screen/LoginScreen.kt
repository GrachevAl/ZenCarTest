package com.example.zencartest.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zencartest.R
import com.example.zencartest.component.ButtonLogInAuthorizationApp
import com.example.zencartest.component.SnackBarToast
import com.example.zencartest.component.TextFieldLogin
import com.example.zencartest.component.TextMainApp
import com.example.zencartest.presentation.event.LoginEvent
import com.example.zencartest.presentation.viewmodel.LoginViewModel
import com.example.zencartest.utils.snackbar.SnackbarManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    onEvent: (LoginEvent) -> Unit,
    goToPageRegister: () -> Unit,
    goToPageListUser: (login: String, password: String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val snackbarMessage by SnackbarManager.snackbarMessages.collectAsState()

    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        snackbarHost = {
            SnackBarToast(
                snackbarMessage = snackbarMessage,
                context = context,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .imePadding(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextMainApp(nameApp = stringResource(R.string.AppName))

                TextFieldLogin(
                    textHint = stringResource(R.string.HintEmail),
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailFocusRequester),
                    keyOption = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                    ),
                    icon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "emailIcon")
                    },
                    value = state.email,
                    onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldLogin(
                    textHint = stringResource(R.string.HintPassword),
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    keyOption = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                    ),
                    icon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "passwordIcon")
                    },
                    value = state.password,
                    onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonLogInAuthorizationApp(
                    onClick = {
                        viewModel.onEvent(LoginEvent.SignIn(state.email, state.password)) {
                            goToPageListUser(state.email, state.password)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = stringResource(R.string.log_in),
                    iconResource = painterResource(id = R.drawable.baseline_login_24),
                )

                ButtonLogInAuthorizationApp(
                    onClick = {
                        goToPageRegister()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = stringResource(R.string.TextRegisterButton),
                    iconResource = painterResource(id = R.drawable.accountuser),
                )
            }
        }
    }
}
