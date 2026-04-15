package com.example.team13projectse.uiscreens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.team13projectse.data.ListingItem
import com.example.team13projectse.viewmodel.CartViewModel
import com.example.team13projectse.viewmodel.ListingViewModel

@Composable
fun ProductListScreen(category: String, cartViewModel: CartViewModel, listingViewModel: ListingViewModel = viewModel()) {
    val products by listingViewModel.getListingsByCategory(category).collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = products) { listing ->
            ListingItem(listing) {
                cartViewModel.addToCart(listing)
            }
        }
    }
}
