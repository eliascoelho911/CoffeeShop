package com.github.eliascoelho911.coffeeshop.presentation.screens.products

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarScrollState
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.coffeeshop.R
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts
import com.github.eliascoelho911.coffeeshop.presentation.components.CoffeeShopMediumTopAppBar
import com.github.eliascoelho911.coffeeshop.presentation.components.CustomScrollableTabRow
import com.github.eliascoelho911.coffeeshop.presentation.components.ProductItem
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel = getViewModel(),
    decayAnimationSpec: DecayAnimationSpec<Float> = rememberSplineBasedDecay(),
    topAppBarScrollState: TopAppBarScrollState = rememberTopAppBarScrollState(),
    scrollBehavior: TopAppBarScrollBehavior = remember {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec, topAppBarScrollState)
    },
) {
    val uiState = remember { viewModel.uiState }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ProductsAppTopBar(
                scrollBehavior = scrollBehavior,
                onSearchAction = {}
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ProductsContent(
                categoriesWithProducts = uiState.categoriesWithProducts,
                categories = uiState.categories,
            )
        }
    }
}

@Composable
private fun ProductsAppTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchAction: () -> Unit,
) {
    CoffeeShopMediumTopAppBar(scrollBehavior = scrollBehavior, actions = {
        IconButton(onClick = onSearchAction) {
            Icon(painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search))
        }
        IconButton(onClick = {}) {
            Icon(painter = painterResource(id = R.drawable.ic_order),
                contentDescription = stringResource(id = R.string.order))
        }
    })
}

@Composable
private fun ProductsContent(
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