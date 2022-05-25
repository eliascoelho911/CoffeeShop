package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.eliascoelho911.coffeeshop.common.Resource
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import com.github.eliascoelho911.coffeeshop.domain.usecases.FindProductsByCategoryId
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

data class ProductsUiState(
    val categories: Resource<List<Category>> = Resource.Loading(),
    val products: Resource<List<Product>> = Resource.Loading(),
)

class ProductsViewModel(
    private val findProductsByCategoryId: FindProductsByCategoryId,
    getAllCategories: GetAllCategories,
) : ViewModel() {

    private val categories = getAllCategories.invoke()

    private val products = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())

    var uiState by mutableStateOf(ProductsUiState())
        private set

    init {
        setCurrentCategoryId(0)

        viewModelScope.launch {
            combine(categories, products) { categories, products ->
                ProductsUiState(categories, products)
            }.collect {
                uiState = it
            }
        }
    }

    fun setCurrentCategoryId(categoryId: Int) {
        val productsOfThisCategory = findProductsByCategoryId.invoke(categoryId)

        viewModelScope.launch {
            products.emitAll(productsOfThisCategory)
        }
    }
}