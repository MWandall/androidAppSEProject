package com.example.team13projectse.uiscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.team13projectse.ui.theme.Blue
import com.example.team13projectse.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListingsScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val user by loginViewModel.isLoggedIn.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Listings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = { /* TODO: Add new listing logic */ },
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Listing")
                Spacer(modifier = Modifier.padding(4.dp))
                Text("Add New")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Listings for ${user?.username ?: "User"}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Blue
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "You have no listings yet.", color = androidx.compose.ui.graphics.Color.Gray)
            }
            
            // TODO: Display user's actual listings here using a LazyColumn
            // LazyColumn {
            //    items(userListings) { listing ->
            //        ListingItem(listing) { /* handle click */ }
            //    }
            // }
        }
    }
}
