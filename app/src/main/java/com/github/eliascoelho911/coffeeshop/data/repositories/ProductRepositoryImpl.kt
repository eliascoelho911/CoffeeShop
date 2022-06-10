package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProductRepositoryImpl(private val context: Context) : ProductRepository {
    override suspend fun getAll(): List<Product> {
        val jsonContent = context.assets.open("coffees_response.json").bufferedReader().use {
            it.readText()
        }
        return Json.decodeFromString(jsonContent)
    }

    override suspend fun findByCategoryId(categoryId: Int): List<Product> {
        return getAll().filter { it.categoryId == categoryId }
    }
}