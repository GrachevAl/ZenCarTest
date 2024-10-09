package com.example.zencartest.presentation.screen

import android.annotation.SuppressLint
import android.os.Environment
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.zencartest.R
import com.example.zencartest.component.DialogWithImage
import com.example.zencartest.component.SnackBarToast
import com.example.zencartest.domain.model.User
import com.example.zencartest.navigation.Destinations
import com.example.zencartest.presentation.event.ListUserEvent
import com.example.zencartest.presentation.viewmodel.ListUserViewModel
import com.example.zencartest.utils.ALBUM_STORAGE
import com.example.zencartest.utils.convertMillisToDate
import com.example.zencartest.utils.convertMillisToDateBirthday
import com.example.zencartest.utils.snackbar.SnackbarManager
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(
    viewModel: ListUserViewModel = hiltViewModel(),
    listDataUsers: Destinations.ListDataUsers,
    onEvent: (ListUserEvent) -> Unit,
    onLogout: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val shouldShowDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val snackbarMessage by SnackbarManager.snackbarMessages.collectAsState()

    LaunchedEffect(Unit) {
        onEvent(ListUserEvent.ShowUsers(listDataUsers.login))
    }

    BackHandler {
        shouldShowDialog.value = true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackBarToast(
                snackbarMessage = snackbarMessage,
                context = context,
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        UserProfileImage(photoUrl = state.photoUrl)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(R.string.User, listDataUsers.login))
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(ListUserEvent.LogOut)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Logout",
                        )
                    }
                },
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (state.listUsers.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp),
                ) {
                    items(state.listUsers, key = { key ->
                        key.id
                    }) { user ->
                        UserCard(user = user, onEvent = onEvent)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

    if (state.logOut) {
        LaunchedEffect(Unit) {
            onLogout()
        }
    }

    if (shouldShowDialog.value) {
        DialogWithImage(
            onDismissRequest = { shouldShowDialog.value = false },
            onConfirmation = {
                onEvent(ListUserEvent.LogOut)
                shouldShowDialog.value = false
            },
            painter = painterResource(id = R.drawable.catmemes),
            imageDescription = "cat",
            text = stringResource(R.string.log_out_of_your_account),
        )
    }
}

@Composable
fun UserCard(user: User, onEvent: (ListUserEvent) -> Unit) {
    val shouldShowDialog = remember { mutableStateOf(false) }
    if (shouldShowDialog.value) {
        DialogWithImage(
            onDismissRequest = { shouldShowDialog.value = false },
            onConfirmation = {
                onEvent(ListUserEvent.DeleteUserById(user.id))
                shouldShowDialog.value = false
            },
            painter = painterResource(id = R.drawable.catmemes),
            imageDescription = "delete",
            text = stringResource(R.string.text_delete_user),
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .pointerInput(UInt) {
                detectTapGestures(
                    onLongPress = {
                        shouldShowDialog.value = true
                    },
                )
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            UserProfileImage(photoUrl = user.photoUrl)

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = stringResource(R.string.birthday, user.birthDate),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )

                val formattedDate = convertMillisToDate(user.timeAdded)
                Text(
                    text = stringResource(R.string.added_text, formattedDate),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )
            }
        }
    }
}

@Composable
fun UserProfileImage(photoUrl: String?) {
    val context = LocalContext.current
    val filePath = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), ALBUM_STORAGE)
    val imageFile = photoUrl?.let { File(filePath, it) }
    if (imageFile != null) {
        if (imageFile.exists()) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageFile)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
        }
    }
}
