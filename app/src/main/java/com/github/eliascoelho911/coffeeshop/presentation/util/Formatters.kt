package com.github.eliascoelho911.coffeeshop.presentation.util

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun Double.formatToCurrency(locale: Locale = Locale.getDefault()): String {
    val format = NumberFormat.getCurrencyInstance(locale)
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance(locale)

    return format.format(this)
}