package com.github.eliascoelho911.coffeeshop.data.repositories

import android.content.Context
import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepositoryImpl(private val context: Context) : CategoryRepository {
    override suspend fun getAll(): List<Category> {
        val jsonContent = context.assets.open("categories_response.json").bufferedReader().use {
            it.readText()
        }
        return Json.decodeFromString(jsonContent)
    }
}