package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.github.eliascoelho911.coffeeshop.common.Resource
import com.github.eliascoelho911.coffeeshop.common.on
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomMediumTopAppBar
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomScrollableTabRow
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme

private class ProductsScreenState(
    val scrollBehavior: TopAppBarScrollBehavior,
)

@Composable
private fun rememberProductsScreenState(
    decayAnimationSpec: DecayAnimationSpec<Float> = rememberSplineBasedDecay(),
    scrollBehavior: TopAppBarScrollBehavior = remember {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    },
) = remember {
    ProductsScreenState(scrollBehavior)
}

@Composable
fun ProductsScreen(viewModel: ProductsViewModel) {
    val screenState = rememberProductsScreenState()
    val uiState = viewModel.uiState

    Scaffold(
        modifier = Modifier.nestedScroll(screenState.scrollBehavior.nestedScrollConnection),
        topBar = { CustomMediumTopAppBar(scrollBehavior = screenState.scrollBehavior) }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ProductsTab(uiState.categories)
        }
    }
}

@Composable
private fun ProductsTab(categories: Resource<List<Category>>) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    categories.on(success = { data ->
        CustomScrollableTabRow(modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex) {
            data.forEach { category ->
                val onClick = { selectedTabIndex = category.id }

                Tab(selected = category.id == selectedTabIndex,
                    onClick = onClick,
                    text = { Text(text = category.name) })
            }
        }
    })
}

@Composable
private fun ProductsTabSuccessPreview() {
    val categories = remember {
        Resource.Success(listOf(
            Category(0, "Primeiro item"),
            Category(1, "Segundo item")))
    }
    ProductsTab(categories)
}

@Composable
@Preview
private fun ProductsSuccessLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        ProductsTabSuccessPreview()
    }
}

@Composable
@Preview
private fun ProductsTabSuccessDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        ProductsTabSuccessPreview()
    }
}