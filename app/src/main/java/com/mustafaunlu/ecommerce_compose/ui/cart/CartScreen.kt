package com.mustafaunlu.ecommerce_compose.ui.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.Loading
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserCartUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.CartViewModel
import com.mustafaunlu.ecommorce_develop.ui.theme.AppTheme

@Composable
fun CartRoute(viewModel: CartViewModel = hiltViewModel()) {
    val cartState by viewModel.userCarts.observeAsState(initial = ScreenState.Loading)
    var counterState by remember {
        mutableIntStateOf(0)
    }
    CartScreen(
        uiState = cartState,
        count = counterState,
        updateCount = { newCount -> counterState = newCount },
    )
}

@Composable
fun CartScreen(uiState: ScreenState<List<UserCartUiData>>, count: Int, updateCount: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is ScreenState.Loading -> {
                Loading()
            }
            is ScreenState.Error -> {
                Error(message = R.string.error)
            }
            is ScreenState.Success -> {
                SuccessScreen(uiData = uiState.uiData, modifier = Modifier.padding(16.dp), count = count, updateCount = updateCount)
            }
        }
    }
}

@Composable
fun SuccessScreen(uiData: List<UserCartUiData>, modifier: Modifier = Modifier, count: Int, updateCount: (Int) -> Unit) {
    Box(modifier = modifier) {
        LazyColumn {
            items(uiData.size) { cart ->
                CartItem(
                    cartUiData = uiData[cart],
                    onCartItemClicked = {},
                    onIncrement = { updateCount.invoke(count + 1) },
                    onDecrement = { updateCount.invoke(count - 1) },
                )
            }
        }
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(15.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Total Amount",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .weight(1f),
                    )
                    Text(
                        text = "1000",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }

                Button(
                    onClick = { /* Handle buy now button click */ },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Buy Now")
                }
            }
        }
    }
}

@Composable
@Preview
fun CartScreenPreview() {
    AppTheme {
        SuccessScreen(uiData = listOfUserCartUiData, modifier = Modifier.padding(16.dp), count = 0, updateCount = {})
    }
}

val listOfUserCartUiData = listOf<UserCartUiData>(
    UserCartUiData(
        userId = 1.toString(),
        productId = 1,
        title = "title",
        price = 100,
        quantity = 1,
        imageUrl = "https://picsum.photos/200/300",
    ),
    UserCartUiData(
        userId = 1.toString(),
        productId = 1,
        title = "title",
        price = 100,
        quantity = 1,
        imageUrl = "https://picsum.photos/200/300",
    ),
    UserCartUiData(
        userId = 1.toString(),
        productId = 1,
        title = "title",
        price = 100,
        quantity = 1,
        imageUrl = "https://picsum.photos/200/300",
    ),
)
