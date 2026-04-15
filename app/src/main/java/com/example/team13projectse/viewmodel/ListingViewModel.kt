package com.example.team13projectse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.team13projectse.data.AppDatabase
import com.example.team13projectse.data.Listing
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListingViewModel(application: Application) : AndroidViewModel(application) {
    private val listingDao = AppDatabase.getDatabase(application).listingDao()

    val allListings: StateFlow<List<Listing>> = listingDao.getAllVisibleListings()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getListingsByCategory(category: String): StateFlow<List<Listing>> {
        return listingDao.getListingsByCategory(category)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }
}
