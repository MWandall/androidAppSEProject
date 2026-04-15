package com.example.team13projectse.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.team13projectse.data.AppDatabase
import com.example.team13projectse.data.User
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun register(onSuccess: (User) -> Unit) {
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill in all fields"
            return
        }

        viewModelScope.launch {
            val existingUser = userDao.getUserByUsername(username)
            if (existingUser != null) {
                errorMessage = "Username already exists"
            } else {
                val newUser = User(
                    username = username,
                    email = email,
                    password = password,
                    isAdmin = false
                )
                userDao.insertUser(newUser)
                // Retrieve the user again to get the generated ID if needed, 
                // or just pass the newUser. Room usually updates the object if it's not a val, 
                // but here it's a data class with val id.
                val savedUser = userDao.getUserByUsername(username)
                if (savedUser != null) {
                    onSuccess(savedUser)
                }
            }
        }
    }
}
