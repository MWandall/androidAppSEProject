package com.example.team13projectse.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.team13projectse.data.CartItem
import com.example.team13projectse.data.Listing

//cart logic will need to be updated to pass in userId. Currently hardcoded to 1. and used as a sort of global cart
class CartViewModel: ViewModel() {
    var cartItems = mutableStateListOf<CartItem>()
        //acts as a temp global cart for testing. will update to use a database
    private set

    // Derived state to calculate total price
    val totalPrice = derivedStateOf {
        cartItems.sumOf { it.price * it.quantity }
    }

    fun addToCart(listing: Listing) {
        val index = cartItems.indexOfFirst { it.listingId == listing.listingId }
        if (index != -1) {
            val item = cartItems[index]
            cartItems[index] = item.copy(quantity = item.quantity + 1)
        } else {
            cartItems.add(CartItem(
                userId = 1,
                listingId = listing.listingId,
                listingName = listing.listingName,
                imageName = listing.imageName,
                price = listing.price))
        }
    }
    
    fun increase(item: CartItem) {
        val index = cartItems.indexOf(item)
        if (index != -1) {
            cartItems[index] = item.copy(quantity = item.quantity + 1)
        }
    }

    fun decrease(item: CartItem) {
        val index = cartItems.indexOf(item)
        if (index != -1) {
            if (item.quantity > 1) {
                cartItems[index] = item.copy(quantity = item.quantity - 1)
            } else {
                cartItems.removeAt(index)
            }
        }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
