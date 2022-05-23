package com.github.eliascoelho911.coffeeshop.presentation.converters

import com.github.eliascoelho911.coffeeshop.domain.entities.Category
import com.github.eliascoelho911.coffeeshop.presentation.vo.CategoryVO

fun Category.toVO(index: Int) = CategoryVO(index, this)