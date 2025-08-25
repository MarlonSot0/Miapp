package com.example.miapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miapp.ui.screen.HomeScreen
import com.example.miapp.ui.screen.LoginScreen
import com.example.miapp.ui.screen.UserScreen
import com.example.miapp.ui.theme.MiappTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.miapp.data.AppDatabase
import com.example.miapp.data.User
import com.example.miapp.ui.screen.ProtectedScreen
import com.example.miapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        lifecycleScope.launch {
            val userCount = userDao.getUserCount()
            if (userCount == 0) {  // Solo insertar si no hay usuarios
                val newUser = User(name = "Admin", email = "Admin@example.com", password = "123456")
                userDao.insert(newUser)
                Log.d("Database", "Usuario insertado: ${newUser.name}")
            } else {
                Log.d("Database", "La base de datos ya tiene usuarios, no se insertó nada.")
            }
        }


        enableEdgeToEdge()
        setContent {
            MiappTheme {
                var currentScreen by remember { mutableStateOf("home") }

                when (currentScreen) {
                    "home" -> Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLoginClick = { currentScreen = "login" },
                            onRegisterClick = { currentScreen = "register" }
                        )
                    }
                    "login" -> Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        LoginScreen(
                            modifier = Modifier.padding(innerPadding),
                            onLoginSuccess = { currentScreen = "protected" }, // Agregado aquí
                            onBack = { currentScreen = "home" },
                            viewModel = userViewModel // Es probable que también necesites pasar el ViewModel aquí
                        )
                    }
                    "register" -> Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPading ->
                        UserScreen(
                            modifier = Modifier.padding(innerPading),
                            onBack = { currentScreen = "home" }
                        )
                    }
                    "protected" -> ProtectedScreen(
                        onLogout = { currentScreen = "home" }, // Volver al inicio al cerrar sesión
                        viewModel = userViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiappTheme {
        Greeting("Android")
    }
}