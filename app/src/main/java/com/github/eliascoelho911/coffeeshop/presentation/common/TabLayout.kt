package com.github.eliascoelho911.coffeeshop.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Shapes
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme

@Composable
fun CustomScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    tabs: @Composable () -> Unit,
) {
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
            )
        },
        containerColor = containerColor,
        contentColor = contentColor,
        tabs = tabs,
        divider = { TabRowDivider() },
        edgePadding = 0.dp
    )
}

@Composable
private fun TabRowDivider() {
    Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = MaterialTheme.colorScheme.surfaceVariant)
}

@Composable
private fun Indicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(4.dp)
        .background(color = MaterialTheme.colorScheme.primary, Shapes.Full)
        .clip(Shapes.Full)
    )
}

@Preview("light", showBackground = true)
@Composable
fun CustomTabRowLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        CustomScrollableTabRow(0) {
            Tab(true, onClick = {}, text = { Text(text = "Cafés quentes") })
            Tab(false, onClick = {}, text = { Text(text = "Cafés gelados") })
            Tab(false, onClick = {}, text = { Text(text = "Bebidas descafeinadas") })
        }
    }
}

@Preview("dark", showBackground = true)
@Composable
fun CustomTabRowDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        CustomScrollableTabRow(0) {
            Tab(true, onClick = {}, text = { Text(text = "Cafés quentes") })
            Tab(false, onClick = {}, text = { Text(text = "Cafés gelados") })
            Tab(false, onClick = {}, text = { Text(text = "Bebidas descafeinadas") })
        }
    }
}