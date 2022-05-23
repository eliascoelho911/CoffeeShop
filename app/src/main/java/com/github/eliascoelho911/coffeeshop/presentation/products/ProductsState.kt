package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.State
import com.github.eliascoelho911.coffeeshop.presentation.common.ResultUi
import com.github.eliascoelho911.coffeeshop.presentation.vo.CategoryVO

class ProductsState(
    val scrollBehavior: TopAppBarScrollBehavior,
    val categories: State<ResultUi<List<CategoryVO>>>,
)