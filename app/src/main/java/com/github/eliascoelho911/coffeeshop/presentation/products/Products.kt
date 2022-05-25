package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomMediumTopAppBar
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomScrollableTabRow
import com.github.eliascoelho911.coffeeshop.presentation.common.ProductItem
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun ProductsScreen(viewModel: ProductsViewModel) {
    val screenState = rememberProductsScreenState()
    val uiState = viewModel.uiState

    Scaffold(
        modifier = Modifier.nestedScroll(screenState.scrollBehavior.nestedScrollConnection),
        topBar = { CustomMediumTopAppBar(scrollBehavior = screenState.scrollBehavior) }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ProductsTab(uiState.categories, onSelectTab = { })
            Products(uiState.categoriesWithProducts)
        }
    }
}

@Composable
fun Products(categoriesWithProducts: List<CategoryWithProducts>) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        categoriesWithProducts.forEach { categoryWithProducts ->
            item(key = "c${categoryWithProducts.category.id}") {
                Text(modifier = Modifier.padding(vertical = 4.dp),
                    text = categoryWithProducts.category.name,
                    style = MaterialTheme.typography.headlineLarge)
            }
            items(categoryWithProducts.products, key = { "p${it.id}" }) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
private fun ProductsTab(
    categories: List<Category>,
    onSelectTab: (index: Int) -> Unit = {},
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    CustomScrollableTabRow(modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex) {
        categories.forEach { category ->
            val onClick = {
                selectedTabIndex = category.index
                onSelectTab(selectedTabIndex)
            }

            Tab(selected = category.index == selectedTabIndex,
                onClick = onClick,
                text = { Text(text = category.name) })
        }
    }
}

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
private fun ProductsTabSuccessPreview() {
    val categories = remember {
        listOf(Category(0, 0, "Primeiro item"),
            Category(1, 0, "Segundo item"))
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