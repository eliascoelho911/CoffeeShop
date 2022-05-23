package com.github.eliascoelho911.coffeeshop.domain.repositories

import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.presentation.common.ResultUi
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAll(): Flow<ResultUi<List<Category>>>
}