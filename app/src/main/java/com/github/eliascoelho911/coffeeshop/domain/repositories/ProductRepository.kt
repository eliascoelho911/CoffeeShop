package com.github.eliascoelho911.coffeeshop.domain.repositories

import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAll(): Flow<List<Product>>

    fun findByCategoryId(categoryId: Int): Flow<List<Product>>
}