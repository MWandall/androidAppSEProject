package com.example.team13projectse.uiscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.team13projectse.viewmodel.CartViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.team13projectse.ui.theme.Blue

@Composable
fun CartScreen(navController: NavController, cartViewModel: CartViewModel) {
    val totalPrice = cartViewModel.totalPrice.value
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .padding(16.dp)
    ) {
        if(cartViewModel.cartItems.isEmpty()) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(size = 120.dp)
                    .background(Color(color = 0xFFF2E7F5),
                        shape = RoundedCornerShape(size = 60.dp)
                    ), contentAlignment = Alignment.Center
                ) {
                    Text(text = ":(", fontSize = 48.sp)
                }
                Spacer(modifier = Modifier.height(height = 12.dp))
                Text(text = "Your cart is empty",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray)
            }
        } else {
            LazyColumn(modifier = Modifier.weight(weight = 1f),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                items(items = cartViewModel.cartItems) { cartItem ->
                    Card(modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(size = 14.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()
                            .padding(all = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val imageResId = context.resources.getIdentifier(cartItem.imageName, "drawable", context.packageName)

                            Image(painter = painterResource(id = if (imageResId != 0) imageResId else android.R.drawable.ic_menu_report_image),
                                contentDescription = null,
                                modifier = Modifier.size(size = 80.dp)
                                    .clip(shape = RoundedCornerShape(size = 12.dp)),
                                contentScale = ContentScale.Crop
                                )
                            Spacer(modifier = Modifier.size(size = 12.dp))
                            Column(modifier = Modifier.weight(weight = 1f)
                            ) {
                                Text(text = cartItem.listingName,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(height = 4.dp))
                                Text(text = "$${cartItem.price}",
                                    fontSize = 14.sp,
                                    color = Color.Blue,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.background(
                                    Color(color = 0xFFF2E7F5),
                                    shape = RoundedCornerShape(size = 24.dp)
                                ).padding(horizontal = 8.dp, vertical = 4.dp)
                            ){
                                IconButton(onClick = { cartViewModel.decrease(cartItem)},
                                    modifier = Modifier.size(size = 32.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Remove,
                                        contentDescription = "Decrease",
                                        tint = Blue)
                                }
                                Text(text = cartItem.quantity.toString(),
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(onClick = { cartViewModel.increase(cartItem)},
                                    modifier = Modifier.size(size = 32.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Add,
                                        contentDescription = "Increase",
                                        tint = Blue
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(height = 12.dp))
            Row(modifier = Modifier.fillMaxWidth()
                .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
                Text(text = "$${String.format("%.2f", totalPrice)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
            }
            Spacer(modifier = Modifier.height(height = 8.dp))
            Button(
                onClick = {navController.navigate(route = "payment")},
                modifier = Modifier.fillMaxWidth()
                    .height(height = 56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                ), shape = RoundedCornerShape(size = 14.dp)
            ) {
                Text(text = "Pay Now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }

        }
    }
}
