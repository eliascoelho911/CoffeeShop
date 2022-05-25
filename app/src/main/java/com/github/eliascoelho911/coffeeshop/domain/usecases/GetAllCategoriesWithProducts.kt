package com.github.eliascoelho911.coffeeshop.domain.usecases

import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts
import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.flow

class GetAllCategoriesWithProducts(
    private val productRepository: ProductRepository,
    private val getAllCategories: GetAllCategories,
) {
    fun invoke() = flow {
        productRepository.getAll().collect { allProducts ->
            getAllCategories.invoke().collect { allCategories ->
                emit(allCategories.map { category ->
                    CategoryWithProducts(
                        category = category,
                        products = allProducts.filter { it.categoryId == category.id }
                    )
                })
            }
        }
    }
}