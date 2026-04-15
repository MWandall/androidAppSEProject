package com.example.team13projectse.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {
    @Query("SELECT * FROM listings WHERE isHidden = 0")
    fun getAllVisibleListings(): Flow<List<Listing>>

    @Query("SELECT * FROM listings")
    fun getAllListings(): Flow<List<Listing>>

    @Query("SELECT * FROM listings WHERE listingId = :listingId")
    suspend fun getListingById(listingId: Int): Listing?

    @Query("SELECT * FROM listings WHERE category = :category AND isHidden = 0")
    fun getListingsByCategory(category: String): Flow<List<Listing>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: Listing)

    @Update
    suspend fun updateListing(listing: Listing)

    @Delete
    suspend fun deleteListing(listing: Listing)
}
