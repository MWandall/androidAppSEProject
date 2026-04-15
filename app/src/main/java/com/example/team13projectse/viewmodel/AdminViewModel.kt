package com.example.team13projectse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.team13projectse.data.AppDatabase
import com.example.team13projectse.data.Listing
import com.example.team13projectse.data.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val userDao = database.userDao()
    private val listingDao = database.listingDao()

    val users: StateFlow<List<User>> = userDao.getAllUsers()
        .map { it.distinctBy { user -> user.username } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val listings: StateFlow<List<Listing>> = listingDao.getAllListings()
        .map { it.distinctBy { listing -> listing.listingName } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
