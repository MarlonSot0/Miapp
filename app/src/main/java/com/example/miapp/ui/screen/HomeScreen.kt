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
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen (modifier : Modifier, onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column (
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Inicar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button (
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Registrarse")
        }
    }
}
