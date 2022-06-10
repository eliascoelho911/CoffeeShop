package com.github.eliascoelho911.coffeeshop.domain.usecases

import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository

class GetAllCategories(private val repository: CategoryRepository) {
    suspend operator fun invoke() = repository.getAll()
}