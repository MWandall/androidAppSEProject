package com.example.team13projectse.data

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.team13projectse.ui.theme.Blue


@Composable
fun ListingItem(listing: Listing, onAdd: () -> Unit) {
    val context = LocalContext.current
    val imageResId = context.resources.getIdentifier(listing.imageName, "drawable", context.packageName)

    Card(modifier = Modifier.padding(all = 8.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blue
        )
    ) {
        Column {
            Box {
                Image(painter = painterResource(id = if (imageResId != 0) imageResId else android.R.drawable.ic_menu_report_image),
                    contentDescription = listing.listingName,
                    modifier = Modifier.fillMaxWidth()
                        .height(height = 140.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(onClick = {
                    onAdd()
                    Toast.makeText(context, "Added to cart",  Toast.LENGTH_SHORT).show()
                },
                    modifier = Modifier.align(Alignment.TopEnd)
                        .padding(all = 8.dp)
                        .background(color = Blue, CircleShape)
                ) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.White
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)
            ) {
                Text(text = listing.listingName,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(height = 4.dp))

                Text(text = "$${listing.price}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
