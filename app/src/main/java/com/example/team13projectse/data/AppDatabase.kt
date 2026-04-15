package com.example.team13projectse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Listing::class, CartItem::class, Order::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun listingDao(): ListingDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "team13_database"
                )
                .addCallback(DatabaseCallback(context))
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val context: Context) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        seedDatabase(database)
                    }
                }
            }

            suspend fun seedDatabase(database: AppDatabase) {
                val listingDao = database.listingDao()
                val userDao = database.userDao()

                // Seed Admin User
                userDao.insertUser(User(username = "admin", password = "password", email = "admin@example.com", isAdmin = true))
                userDao.insertUser(User(username = "sam", password = "password", email = "sam@example.com", isAdmin = false))

                // Seed Listings
                val seedListings = listOf(
                    Listing(listingName = "Microscope", description = "High quality microscope for lab use", category = "Lab Equipment", price = 150.0, imageName = "microscope", sellerId = 1),
                    Listing(listingName = "Beaker Set", description = "Set of 5 glass beakers", category = "Glassware", price = 25.0, imageName = "beaker", sellerId = 1),
                    Listing(listingName = "Bunsen Burner", description = "Standard lab bunsen burner", category = "Lab Equipment", price = 40.0, imageName = "bunsen_burner", sellerId = 1),
                    Listing(listingName = "Test Tube Stand", description = "Wooden test tube holder", category = "Lab Equipment", price = 15.0, imageName = "tes_tube_stand", sellerId = 1),
                    Listing(listingName = "Digital Scale", description = "High precision digital scale", category = "Electronic", price = 85.0, imageName = "digital_scale", sellerId = 1),
                    Listing(listingName = "Lab Coat", description = "White cotton lab coat", category = "Safety", price = 30.0, imageName = "lab_coat", sellerId = 1),
                    Listing(listingName = "Erlenmeyer Flask", description = "250ml Erlenmeyer flask", category = "Glassware", price = 12.0, imageName = "erlenmeyer_flask", sellerId = 1),
                    Listing(listingName = "Pipette", description = "Precision pipette for liquid handling", category = "Lab Equipment", price = 45.0, imageName = "pipette", sellerId = 1),
                    Listing(listingName = "Ph Meter", description = "Digital pH meter for acidity testing", category = "Electronic", price = 60.0, imageName = "ph_meter", sellerId = 1),
                    Listing(listingName = "Autoclave", description = "Steam sterilizer for laboratory tools", category = "Lab Equipment", price = 450.0, imageName = "autoclave", sellerId = 1),
                    Listing(listingName = "Resistors Kit", description = "Box of assorted electrical resistors", category = "Electronic", price = 10.0, imageName = "resistors", sellerId = 1),
                    Listing(listingName = "Vinyl Gloves", description = "Box of 100 disposable vinyl gloves", category = "Safety", price = 15.0, imageName = "vinyl_gloves", sellerId = 1),
                    Listing(listingName = "Calculator", description = "Scientific calculator", category = "Electronic", price = 20.0, imageName = "calculator", sellerId = 1),
                    Listing(listingName = "Circuit Board", description = "Standard breadboard for prototyping", category = "Electronic", price = 12.0, imageName = "circuit_board", sellerId = 1),
                    Listing(listingName = "Digital Caliper", description = "Precision measuring tool", category = "Lab Equipment", price = 35.0, imageName = "digital_caliper", sellerId = 1),
                    Listing(listingName = "Breadboard Kit", description = "Prototyping kit with jump wires", category = "Electronic", price = 25.0, imageName = "breadboard_mod_kit", sellerId = 1),
                    Listing(listingName = "Test Tube", description = "Single glass test tube", category = "Glassware", price = 2.0, imageName = "test_tube", sellerId = 1),
                    Listing(listingName = "Breadboard", description = "Standard 830 point breadboard", category = "Electronic", price = 8.0, imageName = "breadboard", sellerId = 1)
                )

                seedListings.forEach { listingDao.insertListing(it) }
            }
        }
    }
}
