package com.github.eliascoelho911.coffeeshop.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.github.eliascoelho911.coffeeshop.R
import com.github.eliascoelho911.coffeeshop.domain.entities.Product
import com.github.eliascoelho911.coffeeshop.presentation.theme.CoffeeShopTheme
import com.github.eliascoelho911.coffeeshop.presentation.util.formatToCurrency
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ProductItem(modifier: Modifier = Modifier, product: Product) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    Row(modifier.height(96.dp)) {
        Box(modifier = Modifier.size(96.dp)) {
            ProductImage(product, shimmer)
            Label(product)
        }
        Spacer(modifier = Modifier.width(16.dp))
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRef, descriptionRef, valueRef) = createRefs()

            Title(nameRef, product)
            Description(descriptionRef, nameRef, valueRef, product)
            Value(valueRef, product)
        }
    }
}

@Composable
private fun ProductImage(
    product: Product,
    shimmer: Shimmer,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = product.name,
        modifier = ImageModifier,
        contentScale = ContentScale.Crop,
        loading = { Box(ImageModifier.shimmer(shimmer)) }
    )
}

@Composable
private fun BoxScope.Label(product: Product) {
    val labelModifier = Modifier
        .align(Alignment.TopEnd)
        .padding(4.dp)
    when {
        product.isNew && product.isTopSelling -> TopSellingLabel(labelModifier)
        product.isTopSelling -> TopSellingLabel(labelModifier)
        product.isNew -> NewLabel(labelModifier)
    }
}

@Composable
fun TopSellingLabel(modifier: Modifier = Modifier) {
    Label(modifier = modifier, backgroundColor = MaterialTheme.colorScheme.tertiaryContainer) {
        Text(text = stringResource(id = R.string.is_top_selling))
    }
}

@Composable
fun NewLabel(modifier: Modifier = Modifier) {
    Label(modifier = modifier) {
        Text(text = stringResource(id = R.string.is_new))
    }
}

@Composable
private fun ConstraintLayoutScope.Value(
    valueRef: ConstrainedLayoutReference,
    product: Product,
) {
    Text(modifier = Modifier.Companion.constrainAs(valueRef) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }, text = product.value.formatToCurrency(),
        style = MaterialTheme.typography.titleSmall)
}

@Composable
private fun ConstraintLayoutScope.Description(
    descriptionRef: ConstrainedLayoutReference,
    nameRef: ConstrainedLayoutReference,
    valueRef: ConstrainedLayoutReference,
    product: Product,
) {
    Text(modifier = Modifier.Companion.constrainAs(descriptionRef) {
        top.linkTo(nameRef.bottom, 2.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(valueRef.top, 2.dp)
        height = Dimension.fillToConstraints
        width = Dimension.fillToConstraints
    }, text = product.description, style = MaterialTheme.typography.bodySmall,
        overflow = TextOverflow.Ellipsis)
}

@Composable
private fun ConstraintLayoutScope.Title(
    nameRef: ConstrainedLayoutReference,
    product: Product,
) {
    Text(modifier = Modifier.Companion.constrainAs(nameRef) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        width = Dimension.fillToConstraints
    }, text = product.name, style = MaterialTheme.typography.titleSmall,
        overflow = TextOverflow.Ellipsis)
}

private val ImageModifier = Modifier
    .clip(RoundedCornerShape(8.dp))
    .size(96.dp)

private val PreviewProduct = Product(name = "Cappuccino Especial",
    description = "Cappuccino elaborado com espresso e nosso exclusivo blend de chocolate vaporizado, finalizado com chantily e raspas de chocolate. Experimente essa deliciosa bebida!",
    size = "240ml",
    categoryId = 1,
    imageUrl = "https://reviewcafe.com.br/wp-content/uploads/2020/08/cappuccino-feito-em-casa.jpg",
    value = 31.5,
    isTopSelling = false,
    isNew = false
)

@Composable
private fun ProductItemPreview(product: Product) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        ProductItem(product = product)
    }
}

@Preview(name = "light, new")
@Composable
private fun ProductItemNewLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        ProductItemPreview(PreviewProduct.copy(isNew = true, isTopSelling = false))
    }
}

@Preview(name = "dark, new")
@Composable
private fun ProductItemNewDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        ProductItemPreview(PreviewProduct.copy(isNew = true, isTopSelling = false))
    }
}

@Preview(name = "light, top selling")
@Composable
private fun ProductItemTopSellingLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        ProductItemPreview(PreviewProduct.copy(isNew = false, isTopSelling = true))
    }
}

@Preview(name = "dark, top selling")
@Composable
private fun ProductItemTopSellingDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        ProductItemPreview(PreviewProduct.copy(isNew = false, isTopSelling = true))
    }
}

@Preview(name = "light")
@Composable
private fun ProductItemLightPreview() {
    CoffeeShopTheme(darkTheme = false) {
        ProductItemPreview(PreviewProduct)
    }
}

@Preview(name = "dark")
@Composable
private fun ProductItemDarkPreview() {
    CoffeeShopTheme(darkTheme = true) {
        ProductItemPreview(PreviewProduct)
    }
}