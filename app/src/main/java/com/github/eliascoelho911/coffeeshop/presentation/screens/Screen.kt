package com.github.eliascoelho911.coffeeshop.presentation.screens

sealed class Screen(val route: String) {
    object Products: Screen("/products")
}