package com.github.eliascoelho911.coffeeshop.domain.usecases

import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository

class GetAllProducts(private val productRepository: ProductRepository) {
    suspend operator fun invoke() = productRepository.getAll()
}