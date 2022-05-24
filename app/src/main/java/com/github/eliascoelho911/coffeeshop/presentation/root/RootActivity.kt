package com.github.eliascoelho911.coffeeshop.presentation.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.eliascoelho911.coffeeshop.presentation.products.ProductsScreen
import com.github.eliascoelho911.coffeeshop.presentation.products.ProductsViewModel
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : ComponentActivity() {
    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
                ProductsScreen(productsViewModel)
            }
        }
    }
}