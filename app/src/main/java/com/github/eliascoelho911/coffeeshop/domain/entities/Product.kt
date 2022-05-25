package com.github.eliascoelho911.coffeeshop.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("size") val size: String?,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("value_RS") val value: Double,
    @SerialName("is_top_selling") val isTopSelling: Boolean,
    @SerialName("is_new") val isNew: Boolean,
)
