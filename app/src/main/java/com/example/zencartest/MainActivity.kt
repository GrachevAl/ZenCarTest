package com.example.zencartest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.zencartest.domain.model.User
import com.example.zencartest.presentation.screen.ListUsers
import com.example.zencartest.presentation.screen.LoginScreen
import com.example.zencartest.presentation.screen.RegistrationScreen
import com.example.zencartest.ui.theme.ZenCarTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZenCarTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegistrationScreen()
                }
            }
        }
    }
}

fun generateTestUsers(): List<User> {
    return List(15) { index ->
        User(
            id = index.toString(),
            name = "User $index",
            birthDate = "199$index-01-01",
            photoUrl = if (index % 2 == 0) "https://cdn1.ozone.ru/s3/multimedia-1-n/6908895311.jpg" else null, // Используем изображение через одного
            token = "token_$index",
        )
    }
}
