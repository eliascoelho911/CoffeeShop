package com.github.eliascoelho911.coffeeshop.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
)