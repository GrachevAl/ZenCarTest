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

