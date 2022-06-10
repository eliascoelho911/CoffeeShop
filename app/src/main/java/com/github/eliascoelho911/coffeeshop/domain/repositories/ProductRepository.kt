package com.github.eliascoelho911.coffeeshop.domain.repositories

import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAll(): List<Product>

    suspend fun findByCategoryId(categoryId: Int): List<Product>
}