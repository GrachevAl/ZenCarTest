package com.example.zencartest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.zencartest.navigation.Destinations
import com.example.zencartest.navigation.NavHostRoot
import com.example.zencartest.presentation.viewmodel.SplashViewModel
import com.example.zencartest.ui.theme.ZenCarTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZenCarTestTheme {
                val splashScreenState by splashViewModel.state.collectAsState()
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when {
                        splashScreenState.isLoading -> {
                            val splashScreen = installSplashScreen()

                            splashScreen.setKeepOnScreenCondition {
                                splashViewModel.state.value.isLoading
                            }
                        }
                        splashScreenState.isLoggedIn -> {
                            NavHostRoot(
                                navController = navController,
                                startDestination = Destinations.ListDataUsers(
                                    splashScreenState.login,
                                    splashScreenState.password,
                                ),
                            )
                        }
                        else -> {
                            NavHostRoot(navController = navController, startDestination = Destinations.Home)
                        }
                    }
                }
            }
        }
    }
}
