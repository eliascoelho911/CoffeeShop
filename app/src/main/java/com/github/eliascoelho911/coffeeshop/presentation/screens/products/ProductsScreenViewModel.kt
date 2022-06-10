package com.github.eliascoelho911.coffeeshop.presentation.screens.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategories
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategoriesWithProducts
import kotlinx.coroutines.launch

data class ProductsScreenState(
    val categories: List<Category> = emptyList(),
    val categoriesWithProducts: List<CategoryWithProducts> = emptyList(),
)

class ProductsScreenViewModel(
    getAllCategoriesWithProducts: GetAllCategoriesWithProducts,
    getAllCategories: GetAllCategories,
) : ViewModel() {

    var uiState by mutableStateOf(ProductsScreenState())
        private set

    init {
        viewModelScope.launch {
            val categories = getAllCategories()
            val categoriesWithProducts = getAllCategoriesWithProducts()
            uiState = ProductsScreenState(categories, categoriesWithProducts)
        }
    }
}