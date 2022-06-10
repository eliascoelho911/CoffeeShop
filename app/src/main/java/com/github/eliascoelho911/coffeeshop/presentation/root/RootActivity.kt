package com.github.eliascoelho911.coffeeshop.presentation.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.eliascoelho911.coffeeshop.presentation.screens.products.ProductsScreen
import com.github.eliascoelho911.coffeeshop.presentation.screens.products.ProductsScreenViewModel
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
                CoffeeShopApp()
            }
        }
    }
}