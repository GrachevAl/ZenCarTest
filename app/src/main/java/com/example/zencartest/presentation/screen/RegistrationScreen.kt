package com.example.zencartest.presentation.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.zencartest.component.DatePickerModal
import com.example.zencartest.component.TextFieldLogin
import com.example.zencartest.component.TextMainApp
import com.example.zencartest.utils.convertMillisToDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun RegistrationScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var selectedPhoto by rememberSaveable { mutableStateOf<String?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val birthDateFocusRequester = FocusRequester()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    fun onDateSelected(selectedDateMillis: Long?) {
        selectedDateMillis?.let {
            birthDate = convertMillisToDate(it)
        }
        showDatePicker = false
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcherGallery =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.d("uri", "$uri")
            imageUri = uri
           // uri?.let { CreateProductEvent.SetPathToImage(it) }?.let { onEvent(it) }
        }


    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = { onDateSelected(it) },
            onDismiss = { showDatePicker = false },
        )
    }

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
                    .padding(36.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextMainApp(nameApp = "Registration")

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
                        Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon")
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
                        imeAction = ImeAction.Next,
                    ),
                    icon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "passwordIcon")
                    },
                    value = password,
                    onValueChange = { password = it },
                    keyboardActions = KeyboardActions(onNext = {
                        birthDateFocusRequester.requestFocus()
                        showDatePicker = !showDatePicker
                    }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldLogin(
                    textHint = "Date of Birth (DD/MM/YYYY)",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(birthDateFocusRequester),
                    keyOption = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                    ),
                    icon = {
                        IconButton(onClick = { showDatePicker = !showDatePicker }) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "birthDateIcon")
                        }
                    },
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(24.dp))


                Button(
                    onClick = {
                        launcherGallery.launch("image/*")
                        selectedPhoto = "Фото выбрано"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "photoIcon")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = selectedPhoto ?: "Choose a Photo")
                }

                Spacer(modifier = Modifier.height(24.dp))


                ButtonLogInAuthorizationApp(
                    onClick = {
                        // Логика регистрации
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = "Register",
                    iconResource = painterResource(id = R.drawable.baseline_login_24),
                )
            }
        }
    }
}
