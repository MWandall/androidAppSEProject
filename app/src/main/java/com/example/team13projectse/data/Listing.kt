package com.example.team13projectse.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listings")
data class Listing(
    @PrimaryKey(autoGenerate = true) val listingId: Int = 0,
    val sellerId: Int,
    val listingName: String,
    val description: String,
    val category: String,
    val price: Double,
    val imageName: String,
    val isHidden: Boolean = false
)
