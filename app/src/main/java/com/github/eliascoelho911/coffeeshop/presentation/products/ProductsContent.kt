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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomMediumTopAppBar
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomScrollableTabRow
import com.github.eliascoelho911.coffeeshop.presentation.common.ProductItem
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import kotlinx.coroutines.launch

@Composable
fun ProductsScreen(viewModel: ProductsViewModel) {
    val screenState = rememberProductsScreenState()
    val uiState = viewModel.uiState

    Scaffold(
        modifier = Modifier.nestedScroll(screenState.scrollBehavior.nestedScrollConnection),
        topBar = { CustomMediumTopAppBar(scrollBehavior = screenState.scrollBehavior) }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ProductsContent(uiState.categoriesWithProducts, uiState.categories)
        }
    }
}

@Composable
fun ProductsContent(
    categoriesWithProducts: List<CategoryWithProducts>,
    categories: List<Category>,
) {
    val categoryIdToPositionOnList = remember(categoriesWithProducts) {
        val map = mutableMapOf<Int, Int>()
        var previousCategoryIndex = 0
        var numberOfProductsFromPreviousCategory = 0
        categoriesWithProducts.forEach {
            map[it.category.id] = if (map.isEmpty()) 0
            else previousCategoryIndex + numberOfProductsFromPreviousCategory + 1

            previousCategoryIndex = map[it.category.id]!!
            numberOfProductsFromPreviousCategory = it.products.size
        }
        map
    }
    val lazyListState = rememberLazyListState()
    val selectedTabIndex by remember {
        derivedStateOf {
            val categoryId = categoryIdToPositionOnList.filterValues {
                lazyListState.firstVisibleItemIndex >= it
            }.maxByOrNull { it.value }?.key
            categories.singleOrNull { it.id == categoryId }?.index ?: 0
        }
    }
    val lifecycleOwner = rememberCoroutineScope()

    ProductsTab(categories = categories,
        onSelectTab = { categoryId ->
            categoryIdToPositionOnList[categoryId]?.let {
                lifecycleOwner.launch {
                    lazyListState.animateScrollToItem(it)
                }
            }
        },
        selectedTabIndex = selectedTabIndex)

    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = lazyListState) {

        categoriesWithProducts.forEach { categoryWithProducts ->
            item(key = "c${categoryWithProducts.category.id}") {
                Text(modifier = Modifier.padding(vertical = 4.dp),
                    text = categoryWithProducts.category.name,
                    style = MaterialTheme.typography.headlineLarge)
            }
            items(categoryWithProducts.products, key = { product -> "p${product.id}" }) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
private fun ProductsTab(
    categories: List<Category>,
    selectedTabIndex: Int,
    onSelectTab: (categoryId: Int) -> Unit = {},
) {
    CustomScrollableTabRow(modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex) {
        categories.forEach { category ->
            val onClick = {
                onSelectTab(category.id)
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
private fun ProductsTabPreview() {
    val categories = remember {
        listOf(Category(0, 0, "Primeiro item"),
            Category(1, 0, "Segundo item"))
    }
    ProductsTab(categories, 0)
}

@Composable
@Preview
private fun ProductsTabLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        ProductsTabPreview()
    }
}

@Composable
@Preview
private fun ProductsTabDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        ProductsTabPreview()
    }
}