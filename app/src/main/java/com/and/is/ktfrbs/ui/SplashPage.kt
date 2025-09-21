package com.and.`is`.ktfrbs.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.and.`is`.ktfrbs.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, isLoggedIn: Boolean) {
    LaunchedEffect(Unit) {
        delay(5000) // 5 seconds
        if (isLoggedIn) {
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF007BFF)), // blue background
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash), // put your downloaded logo here
            contentDescription = "App Logo",
            modifier = Modifier.size(500.dp)
        )
    }
}
