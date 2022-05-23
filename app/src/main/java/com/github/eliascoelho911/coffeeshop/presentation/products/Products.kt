package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomMediumTopAppBar
import com.github.eliascoelho911.coffeeshop.presentation.common.CustomScrollableTabRow
import com.github.eliascoelho911.coffeeshop.presentation.common.ResultUi
import com.github.eliascoelho911.coffeeshop.presentation.common.SuccessUi
import com.github.eliascoelho911.coffeeshop.presentation.common.on
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import com.github.eliascoelho911.coffeeshop.presentation.vo.CategoryVO

@Composable
fun ProductsScreen(state: ProductsState) {
    Scaffold(
        modifier = Modifier.nestedScroll(state.scrollBehavior.nestedScrollConnection),
        topBar = { CustomMediumTopAppBar(scrollBehavior = state.scrollBehavior) }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            ProductsTab(state.categories)
        }
    }
}

@Composable
private fun ProductsTab(categories: State<ResultUi<List<CategoryVO>>>) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    categories.value.on(success = { data ->
        CustomScrollableTabRow(modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex) {
            data.forEach { category ->
                val onClick = { selectedTabIndex = category.index }

                Tab(selected = category.index == selectedTabIndex,
                    onClick = onClick,
                    text = { Text(text = category.name) })
            }
        }
    })
}

@Composable
private fun ProductsTabSuccessPreview() {
    val categories = remember {
        mutableStateOf(SuccessUi(listOf(
            CategoryVO(0, "Primeiro item"),
            CategoryVO(1, "Segundo item"))))
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