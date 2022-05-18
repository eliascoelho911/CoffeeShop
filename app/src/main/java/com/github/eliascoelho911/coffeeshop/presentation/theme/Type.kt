package com.github.eliascoelho911.coffeeshop.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.eliascoelho911.coffeeshop.R

val CabinFontFamily = FontFamily(
    Font(R.font.cabin_regular),
    Font(R.font.cabin_medium, weight = FontWeight.Medium),
    Font(R.font.cabin_semibold, weight = FontWeight.SemiBold),
    Font(R.font.cabin_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 64.sp,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 52.sp,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 44.sp,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 40.sp,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 36.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 32.sp,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 28.sp,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 6.sp,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = CabinFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.4.sp
    ),
)