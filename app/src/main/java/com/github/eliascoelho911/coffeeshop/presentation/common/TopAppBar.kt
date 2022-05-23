package com.github.eliascoelho911.coffeeshop.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.coffeeshop.R
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme

private val CourgetteFontFamily = FontFamily(Font(R.font.courgette_regular))

@Composable
fun CustomMediumTopAppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer,
        scrolledContainerColor = MaterialTheme.colorScheme.surface),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    MediumTopAppBar(modifier = modifier,
        title = { CoffeeShopLogo() },
        scrollBehavior = scrollBehavior,
        navigationIcon = navigationIcon,
        colors = colors,
        actions = actions)
}

@Composable
private fun CoffeeShopLogo() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineSmall.copy(fontFamily = CourgetteFontFamily),
            maxLines = 1)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(painter = painterResource(id = R.drawable.ic_coffee),
            contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
private fun CoffeeShopLogoPreview() {
    CoffeeShopTheme(darkTheme = false) {
        CoffeeShopLogo()
    }
}

@Preview(name = "medium - light", showBackground = true)
@Composable
private fun CustomMediumTopAppBarLightPreview() {
    CoffeeShopTheme(darkTheme = true) {
        CustomMediumTopAppBar()
    }
}

@Preview(name = "medium - dark", showBackground = true)
@Composable
private fun CustomMediumTopAppBarDarkPreview() {
    CoffeeShopTheme(darkTheme = false) {
        CustomMediumTopAppBar()
    }
}