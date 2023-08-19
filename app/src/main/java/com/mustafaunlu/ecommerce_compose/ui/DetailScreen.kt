package com.mustafaunlu.ecommerce_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce_compose.ui.uiData.DetailProductUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.DetailViewModel

@Composable
fun DetailRoute(viewModel: DetailViewModel = hiltViewModel()) {
    val product by viewModel.product.observeAsState(initial = ScreenState.Loading)

    val onAddToCartButtonClicked: (UserCartEntity) -> Unit =
        { userCart -> viewModel.addToCart(userCart) }

    val onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit =
        { userCartUiData -> viewModel.addToFavorite(userCartUiData) }

    ProductDetailScreen(
        product = product,
        onAddToCartButtonClicked = onAddToCartButtonClicked,
        onAddToFavoritesButtonClicked = onAddToFavoritesButtonClicked,
    )
}

@Composable
fun ProductDetailScreen(
    product: ScreenState<DetailProductUiData>,
    onAddToCartButtonClicked: (UserCartEntity) -> Unit,
    onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (product) {
            is ScreenState.Error -> Error(message = R.string.error)
            ScreenState.Loading -> Loading()
            is ScreenState.Success -> SuccessScreen(
                uiData = product.uiData,
                modifier = Modifier.padding(16.dp),
                onAddToCartButtonClicked = onAddToCartButtonClicked,
                onAddToFavoritesButtonClicked = onAddToFavoritesButtonClicked,

            )
        }
    }
}

@Composable
fun SuccessScreen(
    uiData: DetailProductUiData,
    modifier: Modifier = Modifier,
    onAddToCartButtonClicked: (UserCartEntity) -> Unit,
    onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                // ViewPager2 goes here
            }

            Text(
                text = uiData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = uiData.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = uiData.price,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Rating: ${uiData.rating}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 8.dp),
                )

                // RatingBar goes here
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = {
                        onAddToCartButtonClicked(
                            UserCartEntity(
                                userId = "",
                                productId = uiData.id,
                                quantity = 1,
                                price = uiData.price.toInt(),
                                title = uiData.title,
                                image = uiData.imageUrl[0],
                            ),
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                ) {
                    Text(text = "Add to Cart")
                }

                Button(
                    onClick = {
                        onAddToFavoritesButtonClicked(
                            UserCartEntity(
                                userId = "",
                                productId = uiData.id,
                                quantity = 1,
                                price = uiData.price.toInt(),
                                title = uiData.title,
                                image = uiData.imageUrl[0],
                            ),
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                ) {
                    Text(text = "Add to Favorites")
                }
            }
        }
    }
}
