package com.github.eliascoelho911.coffeeshop.domain.repositories

import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getAll(): List<Category>
}