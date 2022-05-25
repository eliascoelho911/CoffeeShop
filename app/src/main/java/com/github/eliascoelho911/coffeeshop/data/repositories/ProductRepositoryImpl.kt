package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProductRepositoryImpl(private val context: Context) : ProductRepository {
    override fun getAll(): Flow<List<Product>> = flow {
        runCatching {
            context.assets.open("coffees_response.json").bufferedReader().use {
                it.readText()
            }
        }.onSuccess {
            emit(Json.decodeFromString(it))
        }
    }

    override fun findByCategoryId(categoryId: Int): Flow<List<Product>> = flow {
        getAll().collect { data ->
            emit(data.filter { it.categoryId == categoryId })
        }
    }
}