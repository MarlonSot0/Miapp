package com.example.miapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miapp.viewmodel.UserViewModel

@Composable
fun UserScreen(modifier : Modifier, onBack: () -> Unit, viewModel: UserViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var register by remember {mutableStateOf(false)}

    Column(modifier = Modifier.padding(25.dp).paddingFromBaseline(top = 120.dp)) {

        if (register) {
            Text("Usuario Registrado", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.addUser(name, email, password)

                    name = ""
                    email = ""
                    password = ""
                    register = true // usuario registrado
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}