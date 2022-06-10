package com.github.eliascoelho911.coffeeshop.di

import com.github.eliascoelho911.coffeeshop.data.repositories.CategoryRepositoryImpl
import com.github.eliascoelho911.coffeeshop.data.repositories.ProductRepositoryImpl
import com.github.eliascoelho911.coffeeshop.domain.repositories.CategoryRepository
import com.github.eliascoelho911.coffeeshop.domain.repositories.ProductRepository
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategoriesWithProducts
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllCategories
import com.github.eliascoelho911.coffeeshop.domain.usecases.GetAllProducts
import com.github.eliascoelho911.coffeeshop.presentation.screens.products.ProductsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single { GetAllCategories(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single { GetAllCategoriesWithProducts(get(), get()) }
    single { GetAllProducts(get()) }
    viewModel { ProductsScreenViewModel(get(), get()) }
}