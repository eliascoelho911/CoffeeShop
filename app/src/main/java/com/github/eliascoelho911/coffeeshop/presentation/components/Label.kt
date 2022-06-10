package com.github.eliascoelho911.coffeeshop.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme

val LabelShape = Shapes.Full.copy(bottomEnd = CornerSize(0.dp))

@Composable
fun Label(
    modifier: Modifier = Modifier,
    shape: Shape = LabelShape,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    content: @Composable () -> Unit,
) {
    Surface(shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier) {
        Box(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            CompositionLocalProvider(
                LocalTextStyle provides textStyle,
            ) {
                content()
            }
        }
    }
}

@Preview(name = "dark")
@Composable
private fun LabelDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        Label {
            Text(text = "Novo")
        }
    }
}

@Preview(name = "light")
@Composable
private fun LabelLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        Label {
            Text(text = "Novo")
        }
    }
}