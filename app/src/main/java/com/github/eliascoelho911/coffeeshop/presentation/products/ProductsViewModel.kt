package com.github.eliascoelho911.coffeeshop.presentation.products

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

data class ProductsUiState(
    val categories: List<Category> = emptyList(),
    val categoriesWithProducts: List<CategoryWithProducts> = emptyList(),
)

class ProductsViewModel(
    getAllCategoriesWithProducts: GetAllCategoriesWithProducts,
    getAllCategories: GetAllCategories,
) : ViewModel() {

    var uiState by mutableStateOf(ProductsUiState())
        private set

    init {
        viewModelScope.launch {
            val categories = getAllCategories()
            val categoriesWithProducts = getAllCategoriesWithProducts()
            ProductsUiState(categories, categoriesWithProducts)
        }
    }
}