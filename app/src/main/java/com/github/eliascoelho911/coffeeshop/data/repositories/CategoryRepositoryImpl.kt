package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository
import com.github.eliascoelho911.coffeeshop.presentation.common.ResultUi
import com.github.eliascoelho911.coffeeshop.presentation.common.emitFailure
import com.github.eliascoelho911.coffeeshop.presentation.common.emitSuccess
import com.github.eliascoelho911.coffeeshop.presentation.common.resultFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepositoryImpl(private val context: Context) : CategoryRepository {
    override fun getAll(): Flow<ResultUi<List<Category>>> = resultFlow {
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