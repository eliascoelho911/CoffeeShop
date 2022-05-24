package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.common.Resource
import com.github.eliascoelho911.coffeeshop.common.emitFailure
import com.github.eliascoelho911.coffeeshop.common.emitSuccess
import com.github.eliascoelho911.coffeeshop.common.resourceFlow
import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProductRepositoryImpl(private val context: Context) : ProductRepository {
    override fun getAll(): Flow<Resource<List<Product>>> = resourceFlow {
        runCatching {
            context.assets.open("coffees_response.json").bufferedReader().use {
                it.readText()
            }
        }.onSuccess {
            emitSuccess(Json.decodeFromString(it))
        }.onFailure {
            emitFailure(it)
        }
    }

    override fun findByCategoryId(categoryId: Int): Flow<Resource<List<Product>>> = resourceFlow {
        getAll().collect { resource ->
            if (resource is Resource.Success) {
                emit(Resource.Success(resource.data.filter { it.categoryId == categoryId }))
            } else {
                emit(resource)
            }
        }
    }
}