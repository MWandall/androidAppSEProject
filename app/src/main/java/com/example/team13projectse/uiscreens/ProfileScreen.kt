package com.example.team13projectse.uiscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.team13projectse.R
import com.example.team13projectse.ui.theme.Blue
import com.example.team13projectse.viewmodel.LoginViewModel


@Composable
fun ProfileScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val user by loginViewModel.isLoggedIn.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                //all profile pics prolly just logo for now
                painter = painterResource(id = R.drawable.scientist_sam),
                contentDescription = null,
                modifier = Modifier.size(size = 96.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = user?.username ?: "Scientist Sam",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))

            MenuOption(
                icon = Icons.Default.Person,
                text = "Personal Details",
                onClick = {}
            )
            MenuOption(
                icon = Icons.Default.Sell,
                text = "Listings",
                onClick = { navController.navigate("userListings") }
            )
            MenuOption(
                icon = Icons.Default.Settings,
                text = "Settings",
                onClick = { navController.navigate("settings") }
            )

            // Admin Button - only visible to admins
            if (user?.isAdmin == true) {
                MenuOption(
                    icon = Icons.Default.AdminPanelSettings,
                    text = "Admin Dashboard",
                    onClick = { navController.navigate("admin") }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        loginViewModel.logout()
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Blue,
                    contentColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.ExitToApp,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.weight(10f))
                    Text(text = "Logout",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MenuOption(icon: ImageVector, text: String, onClick: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon,
                contentDescription = null,
                tint = Blue
            )
            Spacer(modifier = Modifier.weight(16f))
            Text(text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
