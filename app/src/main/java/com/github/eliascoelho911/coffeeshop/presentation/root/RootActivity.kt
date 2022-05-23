package com.github.eliascoelho911.coffeeshop.presentation.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import com.github.eliascoelho911.coffeeshop.presentation.common.asState
import com.github.eliascoelho911.coffeeshop.presentation.products.ProductsScreen
import com.github.eliascoelho911.coffeeshop.presentation.products.ProductsState
import com.github.eliascoelho911.coffeeshop.presentation.products.ProductsViewModel
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : ComponentActivity() {
    private val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeShopTheme {
                val decayAnimationSpec = rememberSplineBasedDecay<Float>()
                val scrollBehavior = remember(decayAnimationSpec) {
                    TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
                }
                val state = ProductsState(
                    scrollBehavior = scrollBehavior,
                    categories = productsViewModel.categories.asState()
                )
                ProductsScreen(state)
            }
        }
    }
}