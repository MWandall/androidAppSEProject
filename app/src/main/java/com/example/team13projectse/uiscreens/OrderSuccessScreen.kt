package com.example.team13projectse.uiscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.team13projectse.ui.theme.Blue
import com.example.team13projectse.viewmodel.CartViewModel

@Composable
fun OrderSuccessScreen(navController: NavController,
                       cartViewModel: CartViewModel
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(size = 120.dp)
            .background(
                color = Color(color = 0xFFE8F5E9),
                shape = CircleShape
            ), contentAlignment = Alignment.Center
            ) {
            Icon(imageVector = Icons.Default.Check,
                contentDescription = "Success!",
                tint = Color(color = 0xFF2E7D32),
                modifier = Modifier.size(size = 34.dp)
            )
        }
        Spacer(modifier = Modifier.size(size = 24.dp))
        Text(text = "Order Placed!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(height = 32.dp))

        Button(onClick = {
            cartViewModel.clearCart()
            navController.navigate(route = "home")
        },
            modifier = Modifier.fillMaxWidth(fraction = 0.75f)
                .height(height = 56.dp),
            shape = RoundedCornerShape(size = 14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text(text = "Continue Shopping",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}