package com.github.eliascoelho911.coffeeshop.domain.usecases

import com.github.eliascoelho911.coffeeshop.domain.entities.CategoryWithProducts

class GetAllCategoriesWithProducts(
    private val getAllProducts: GetAllProducts,
    private val getAllCategories: GetAllCategories,
) {
    suspend operator fun invoke(): List<CategoryWithProducts> {
        val allProducts = getAllProducts()
        val allCategories = getAllCategories()
        return allCategories.map { category ->
            CategoryWithProducts(
                category = category,
                products = allProducts.filter { it.categoryId == category.id }
            )
        }
    }
}