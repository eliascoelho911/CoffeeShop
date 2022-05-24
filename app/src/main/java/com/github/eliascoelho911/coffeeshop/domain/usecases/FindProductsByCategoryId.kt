package com.github.eliascoelho911.coffeeshop.domain.usecases

import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository

class FindProductsByCategoryId(private val repository: ProductRepository) {
    fun invoke(categoryId: Int) = repository.findByCategoryId(categoryId)
}