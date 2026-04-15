package com.example.team13projectse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.team13projectse.navigation.AppNavGraph
import com.example.team13projectse.ui.theme.Team13ProjectSETheme
import com.example.team13projectse.viewmodel.CartViewModel
import com.example.team13projectse.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val cartViewModel: CartViewModel = viewModel()
            
            Team13ProjectSETheme(darkTheme = themeViewModel.isDarkMode) {
                AppNavGraph(cartViewModel, themeViewModel)
            }
        }
    }
}
