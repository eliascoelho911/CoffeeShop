package com.github.eliascoelho911.coffeeshop.domain.repositories

import com.github.eliascoelho911.coffeeshop.common.Resource
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAll(): Flow<Resource<List<Category>>>
}