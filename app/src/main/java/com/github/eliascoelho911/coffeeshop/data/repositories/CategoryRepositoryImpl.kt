package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepositoryImpl(private val context: Context) : CategoryRepository {
    override fun getAll(): Flow<List<Category>> = flow {
        runCatching {
            context.assets.open("categories_response.json").bufferedReader().use {
                it.readText()
            }
        }.onSuccess {
            emit(Json.decodeFromString(it))
        }
    }
}