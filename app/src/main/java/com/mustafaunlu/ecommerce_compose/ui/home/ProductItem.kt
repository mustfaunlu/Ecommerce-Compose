package com.mustafaunlu.ecommerce_compose.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.ui.uiData.ProductUiData

@Composable
fun ProductItem(
    product: ProductUiData,
    onProductClicked: (ProductUiData) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).clickable { onProductClicked(product) },
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Column(
            modifier = Modifier.clickable { onProductClicked.invoke(product) }
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = stringResource(id = R.string.product_image_content),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = product.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )

            Text(
                text = product.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )

            Text(
                text = "Rating ${product.rating}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )

            Text(
                text = "${product.price} TL",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            )
        }
    }
}

@Composable
fun ProductList(
    products: List<ProductUiData>,
    onProductClicked: (ProductUiData) -> Unit,
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(products.size) {
            ProductItem(
                product = products[it],
                onProductClicked = onProductClicked,
            )
        }
    }
}

@Composable
@Preview
fun PreviewProductItem() {
    ProductItem(
        product = ProductUiData(
            id = 1,
            title = "Product 1",
            description = "Product 1 description",
            rating = 4.5,
            price = "$10",
            imageUrl = "imageString",
        ),
        onProductClicked = {},
    )
}

data class Product(
    val title: String,
    val description: String,
    val rating: Float,
    val price: String,
    val imageString: String,
)
