package com.github.eliascoelho911.coffeeshop.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun CoffeeShopLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineSmall.copy(fontFamily = CourgetteFontFamily),
            maxLines = 1,
            color = color)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(painter = painterResource(id = R.drawable.ic_coffee),
            contentDescription = "",
            tint = color)
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeShopLogoPreview() {
    CoffeeShopTheme(darkTheme = false) {
        CoffeeShopLogo()
    }
}