package com.example.team13projectse.uiscreens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team13projectse.ui.theme.Blue


//TODO mock payment update here!!!!!!!!!
@Composable
fun PaymentScreen(navController: NavController) {

    LaunchedEffect(key1 = Unit) {
        delay(timeMillis = 3000)
        navController.navigate(route = "success") {
            popUpTo(route = "cart") {inclusive = true}
        }
    }
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    val dots by infiniteTransition.animateValue(
        initialValue = 0,
        targetValue = 3,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Restart
        ), label = "dotAnimation"
    )
    val loadingText = "Payment Processing" + ".".repeat(n = dots)

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally
        )  {
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                color = Blue,
                modifier = Modifier.size(size = 64.dp)
            )
            Spacer(modifier = Modifier.height(height = 20.dp))
            Text(text = loadingText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Blue)

        }
    }
}