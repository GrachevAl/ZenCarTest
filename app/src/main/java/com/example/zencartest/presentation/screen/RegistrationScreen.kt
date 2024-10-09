package com.example.zencartest.presentation.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zencartest.R
import com.example.zencartest.component.ButtonLogInAuthorizationApp
import com.example.zencartest.component.DatePickerModal
import com.example.zencartest.component.SnackBarToast
import com.example.zencartest.component.TextFieldLogin
import com.example.zencartest.component.TextMainApp
import com.example.zencartest.presentation.event.RegistrationEvent
import com.example.zencartest.presentation.viewmodel.RegistrationViewModel
import com.example.zencartest.utils.convertMillisToDate
import com.example.zencartest.utils.convertToDateTextField
import com.example.zencartest.utils.snackbar.SnackbarManager
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>(),
    onNavigate: (login: String, password: String) -> Unit,
    onEvent: (RegistrationEvent) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    var birthDate by rememberSaveable { mutableStateOf(state.birthDate) }
    var selectedPhoto by rememberSaveable { mutableStateOf<String?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }

    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val birthDateFocusRequester = FocusRequester()

    val context = LocalContext.current
    val snackbarMessage by SnackbarManager.snackbarMessages.collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    fun onDateSelected(selectedDateMillis: Long?) {
        selectedDateMillis?.let {
            val selectedDate = convertToDateTextField(it)
            onEvent(RegistrationEvent.SetBirthDate(selectedDate))
            birthDate = selectedDate
        }
        showDatePicker = false
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcherGallery =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                onEvent(RegistrationEvent.SetPathToImage(it))
                selectedPhoto = context.getString(R.string.PhotoSelectedText)
            }
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
                    .padding(36.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextMainApp(nameApp = stringResource(R.string.RegistrationTextScreen))

                TextFieldLogin(
                    textHint = stringResource(id = R.string.HintEmail),
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
                    value = state.emailOrName,
                    onValueChange = { onEvent(RegistrationEvent.SetNameUser(it)) },
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldLogin(
                    textHint = stringResource(id = R.string.HintPassword),
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
                    value = state.password,
                    onValueChange = { onEvent(RegistrationEvent.SetPassword(it)) },
                    keyboardActions = KeyboardActions(onNext = {
                        birthDateFocusRequester.requestFocus()
                        showDatePicker = !showDatePicker
                    }),
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldLogin(
                    textHint = stringResource(R.string.date_of_birth),
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
                    value = state.birthDate,
                    onValueChange = { },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        launcherGallery.launch("image/*")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.White,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "photoIcon")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (state.imagePath?.isNotEmpty() == true) {
                            stringResource(
                                id = R.string.PhotoSelectedText,
                            )
                        } else {
                            stringResource(
                                R.string.Choose_a_photo,
                            )
                        },
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                ButtonLogInAuthorizationApp(
                    onClick = {
                        onEvent(RegistrationEvent.RegistrationUser)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White,
                    ),
                    text = stringResource(id = R.string.RegistrationTextScreen),
                    iconResource = painterResource(id = R.drawable.baseline_login_24),
                )
            }
        }
    }
    if (state.isRegistrationSuccess) {
        LaunchedEffect(Unit) {
            onNavigate(state.emailOrName, state.password)
        }
    }
}
