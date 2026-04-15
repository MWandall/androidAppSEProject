package com.example.team13projectse.uiscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.team13projectse.ui.theme.Blue
import com.example.team13projectse.viewmodel.AdminViewModel

@Composable
fun AdminScreen(navController: NavHostController, adminViewModel: AdminViewModel = viewModel()) {
    var selectedView by remember { mutableStateOf("menu") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selectedView != "menu") {
                IconButton(onClick = { selectedView = "menu" }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            } else {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to Profile")
                }
            }
            Text(
                text = "Admin Dashboard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Blue,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        when (selectedView) {
            "menu" -> AdminMenu { selectedView = it }
            "users" -> UserList(adminViewModel)
            "listings" -> AdminListingList(adminViewModel)
        }
    }
}

@Composable
fun AdminMenu(onSelection: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onSelection("users") },
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text("Manage Users")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSelection("listings") },
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text("Manage Listings")
        }
    }
}

@Composable
fun UserList(viewModel: AdminViewModel) {
    val users by viewModel.users.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(users) { user ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Username: ${user.username}", fontWeight = FontWeight.Bold)
                    Text(text = "Email: ${user.email}")
                    Text(
                        text = if (user.isAdmin) "Role: Admin" else "Role: User",
                        color = if (user.isAdmin) Blue else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun AdminListingList(viewModel: AdminViewModel) {
    val listings by viewModel.listings.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(listings) { listing ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = listing.listingName, fontWeight = FontWeight.Bold)
                    Text(text = "Price: $${listing.price}")
                    Text(text = "Category: ${listing.category}")
                    Text(
                        text = if (listing.isHidden) "Status: Hidden" else "Status: Visible",
                        color = if (listing.isHidden) Color.Red else Color.Green
                    )
                }
            }
        }
    }
}
