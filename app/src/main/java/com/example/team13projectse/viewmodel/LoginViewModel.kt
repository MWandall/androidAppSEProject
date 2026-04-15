package com.example.team13projectse.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.team13projectse.data.AppDatabase
import com.example.team13projectse.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    private val _isLoggedIn = MutableStateFlow<User?>(null)
    val isLoggedIn: StateFlow<User?> = _isLoggedIn

    fun login(onSuccess: (User) -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUserByUsername(email) // Using email field as username for now as per seed data
            if (user != null && user.password == password) {
                _isLoggedIn.value = user
                errorMessage = null
                onSuccess(user)
            } else {
                errorMessage = "Invalid email or password. Please try again."
            }
        }
    }

    fun setLoggedInUser(user: User) {
        _isLoggedIn.value = user
    }

    fun logout() {
        _isLoggedIn.value = null
        email = ""
        password = ""
    }

    fun deleteAccount(onSuccess: () -> Unit) {
        val currentUser = _isLoggedIn.value
        if (currentUser != null) {
            viewModelScope.launch {
                userDao.deleteUser(currentUser)
                logout()
                onSuccess()
            }
        }
    }
}
