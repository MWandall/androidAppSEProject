package com.example.team13projectse.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.team13projectse.uiscreens.AdminScreen
import com.example.team13projectse.uiscreens.CartScreen
import com.example.team13projectse.uiscreens.HomeScreen
import com.example.team13projectse.uiscreens.LoginScreen
import com.example.team13projectse.uiscreens.OrderSuccessScreen
import com.example.team13projectse.uiscreens.PaymentScreen
import com.example.team13projectse.uiscreens.ProductListScreen
import com.example.team13projectse.uiscreens.ProfileScreen
import com.example.team13projectse.uiscreens.RegisterScreen
import com.example.team13projectse.uiscreens.SearchScreen
import com.example.team13projectse.uiscreens.SettingsScreen
import com.example.team13projectse.uiscreens.UserListingsScreen
import com.example.team13projectse.viewmodel.CartViewModel
import com.example.team13projectse.viewmodel.LoginViewModel
import com.example.team13projectse.viewmodel.RegisterViewModel
import com.example.team13projectse.viewmodel.ThemeViewModel

@Composable
fun AppNavGraph(cartViewModel: CartViewModel, themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val userState = loginViewModel.isLoggedIn.collectAsState()

    Scaffold(
        bottomBar = {
            if (userState.value != null) {
                BottomBarNav(navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController, startDestination = "login"
            ) {
                composable(route = "login") {
                    LoginScreen(navController, loginViewModel)
                }
                composable(route = "register") {
                    val registerViewModel: RegisterViewModel = viewModel()
                    RegisterScreen(navController, registerViewModel) { user ->
                        loginViewModel.setLoggedInUser(user)
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
                composable(route = "home") {
                    HomeScreen(navController, loginViewModel)
                }
                composable(
                    route = "products?category={category}",
                    arguments = listOf(navArgument("category") { type = NavType.StringType })
                ) { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: ""
                    ProductListScreen(category, cartViewModel)
                }
                composable(route = "search") {
                    SearchScreen(cartViewModel)
                }
                composable(route = "cart") {
                    CartScreen(navController, cartViewModel)
                }
                composable(route = "payment") {
                    PaymentScreen(navController)
                }
                composable(route = "success") {
                    OrderSuccessScreen(navController, cartViewModel)
                }
                composable(route = "profile") {
                    ProfileScreen(navController, loginViewModel)
                }
                composable(route = "settings") {
                    SettingsScreen(navController, themeViewModel, loginViewModel)
                }
                composable(route = "admin") {
                    AdminScreen(navController)
                }
                composable(route = "userListings") {
                    UserListingsScreen(navController, loginViewModel)
                }
            }
        }
    }
}

@Composable
fun BottomBarNav(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    NavigationBar(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
            )
        ),
        containerColor = Color.Transparent,
        tonalElevation = 6.dp
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            val selected = currentRoute == item.screenRoute

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                )
            )
        }
    }
}
