package com.example.miapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.miapp.data.AppDatabase
import com.example.miapp.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel (application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val userDao = db.userDao()

    fun addUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            val existingUser = userDao.getUserByEmail(email)

            if (existingUser == null) {
                val newUser = User(name = name, email = email, password = password)
                userDao.insert(newUser)
                Log.d("Database", "Usuario insertado: ${newUser.name}")
            } else {
                Log.d("Database", "El usuario con este email ya existe.")
            }
        }
    }

    fun validateLogin(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.login(email, password)
            onResult(user != null) // Devuelve true si el usuario existe, false si no
        }
    }

    val users: Flow<List<User>> = userDao.getAllUsers()
}


