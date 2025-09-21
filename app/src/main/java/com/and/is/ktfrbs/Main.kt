package com.and.`is`.ktfrbs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.and.`is`.ktfrbs.ui.LoginScreen
import com.and.`is`.ktfrbs.ui.RegisterScreen
import com.and.`is`.ktfrbs.ui.SplashScreen
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val navController = rememberNavController()
            val auth = FirebaseAuth.getInstance()

            // Observe auth state
            val currentUser = remember { mutableStateOf(auth.currentUser) }

            LaunchedEffect(auth) {
                auth.addAuthStateListener { firebaseAuth ->
                    currentUser.value = firebaseAuth.currentUser
                }
            }

            Surface(color = MaterialTheme.colorScheme.background) {
                AppNavHost(navController, currentUser.value != null)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, isLoggedIn: Boolean) {
    NavHost(
        navController = navController,
        startDestination = "splash" // 👈 Start at splash screen
    ) {
        composable("splash") { SplashScreen(navController, isLoggedIn) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("main") { MainScreen(navController) }
    }
}
