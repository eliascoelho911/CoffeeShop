package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.common.Resource
import com.github.eliascoelho911.coffeeshop.common.emitFailure
import com.github.eliascoelho911.coffeeshop.common.emitSuccess
import com.github.eliascoelho911.coffeeshop.common.resourceFlow
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepositoryImpl(private val context: Context) : CategoryRepository {
    override fun getAll(): Flow<Resource<List<Category>>> = resourceFlow {
        runCatching {
            context.assets.open("categories_response.json").bufferedReader().use {
                it.readText()
            }
        }.onSuccess {
            emitSuccess(Json.decodeFromString(it))
        }.onFailure {
            emitFailure(it)
        }
    }
}