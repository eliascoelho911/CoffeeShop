package com.github.eliascoelho911.coffeeshop.presentation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.eliascoelho911.coffeeshop.presentation.screens.Screen
import com.github.eliascoelho911.coffeeshop.presentation.screens.products.ProductsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun CoffeeShopApp() {
    val coffeeShopState = rememberCoffeeShopState()
    AnimatedNavHost(
        navController = coffeeShopState.navController,
        startDestination = Screen.Products.route
    ) {
        composable(Screen.Products.route) {
            ProductsScreen()
        }
    }
}

class CoffeeShopState(val navController: NavHostController)

@Composable
fun rememberCoffeeShopState(
    navController: NavHostController = rememberAnimatedNavController(navigators = arrayOf()),
) = CoffeeShopState(navController)