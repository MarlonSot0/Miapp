package com.example.miapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miapp.viewmodel.UserViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
@Composable
fun ProtectedScreen(onLogout: () -> Unit, viewModel: UserViewModel = viewModel()) {
    val users by viewModel.users.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("👋 Bienvenido a la vista protegida", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Usuarios Registrados:", fontWeight = FontWeight.Bold)

        users.forEach { user ->
            Text(text = "• ${user.name} 📧 ${user.email}")
        }

        Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
            Text("Cerrar Sesión")
        }
    }
}