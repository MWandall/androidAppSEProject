package com.example.team13projectse.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val listingId: Int,
    val listingName: String,
    val imageName: String,
    val price: Double,
    val quantity: Int = 1
)
