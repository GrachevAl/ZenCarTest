package com.example.zencartest.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zencartest.R
import com.example.zencartest.component.ButtonLogInAuthorizationApp
import com.example.zencartest.component.TextFieldLogin
import com.example.zencartest.component.TextMainApp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()

    val focusManager = LocalFocusManager.current

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
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
                TextMainApp(nameApp = "ZenCar")

                TextFieldLogin(
                    textHint = "Email",
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
                    value = email,
                    onValueChange = { email = it },
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldLogin(
                    textHint = "Password",
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
                    value = password,
                    onValueChange = { password = it },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonLogInAuthorizationApp(
                    onClick = {
                        //
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = "Log In",
                    iconResource = painterResource(id = R.drawable.baseline_login_24),
                )

                ButtonLogInAuthorizationApp(
                    onClick = {
                        // Ваш обработчик регистрации
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = "Register",
                    iconResource = painterResource(id = R.drawable.accountuser),
                )
            }
        }
    }
}
