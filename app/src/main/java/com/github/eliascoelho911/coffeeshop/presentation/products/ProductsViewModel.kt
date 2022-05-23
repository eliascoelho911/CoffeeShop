package com.github.eliascoelho911.coffeeshop.presentation.products

import androidx.lifecycle.ViewModel
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategories
import com.github.eliascoelho911.coffeeshop.presentation.common.convertToVO
import com.github.eliascoelho911.coffeeshop.presentation.converters.toVO

class ProductsViewModel(getAllCategories: GetAllCategories) : ViewModel() {
    val categories = getAllCategories.invoke().convertToVO { list ->
        list.mapIndexed { index, category -> category.toVO(index) }
    }
}