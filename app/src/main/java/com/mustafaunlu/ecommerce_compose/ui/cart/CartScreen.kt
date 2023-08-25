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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.Loading
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserCartUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.CartViewModel

@Composable
fun CartRoute(
    viewModel: CartViewModel = hiltViewModel(),
    onClickedBuyNowButton: () -> Unit,
    onProductClicked: (UserCartUiData) -> Unit,
) {
    val cartState by viewModel.userCarts.observeAsState(initial = ScreenState.Loading)
    val totalPrice by viewModel.totalPriceLiveData.observeAsState(initial = 0.0)

    val updateCartItem = { cartUiData: UserCartUiData ->
        viewModel.updateUserCartItem(cartUiData)
    }

    val updateTotalAmount = { uiData: List<UserCartUiData> ->
        viewModel.updateTotalPrice(uiData)
    }

    val onDecrement = { cartUiData: UserCartUiData ->
        if (cartUiData.quantity > 1) {
            val updated = cartUiData.copy(quantity = cartUiData.quantity.dec())
            updateCartItem(updated)
        }
    }

    val onIncrement = { cartUiData: UserCartUiData ->
        val updated = cartUiData.copy(quantity = cartUiData.quantity.inc())
        updateCartItem(updated)
    }

    CartScreen(
        uiState = cartState,
        onClickedBuyNowButton = onClickedBuyNowButton,
        onProductClicked = onProductClicked,
        totalPrice = totalPrice,
        onDecrement = onDecrement,
        onIncrement = onIncrement,
        updateTotalAmount = updateTotalAmount,
    )
}

@Composable
fun CartScreen(
    uiState: ScreenState<List<UserCartUiData>>,
    onClickedBuyNowButton: () -> Unit,
    onProductClicked: (UserCartUiData) -> Unit,
    totalPrice: Double,
    onDecrement: (UserCartUiData) -> Unit,
    onIncrement: (UserCartUiData) -> Unit,
    updateTotalAmount: (List<UserCartUiData>) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is ScreenState.Loading -> {
                Loading()
            }

            is ScreenState.Error -> {
                Error(message = uiState.message)
            }

            is ScreenState.Success -> {
                SuccessScreen(
                    uiData = uiState.uiData,
                    modifier = Modifier.padding(16.dp),
                    onProductClicked = onProductClicked,
                    onClickedBuyNowButton = onClickedBuyNowButton,
                    totalPrice = totalPrice,
                    onDecrement = onDecrement,
                    onIncrement = onIncrement,
                    updateTotalAmount = updateTotalAmount,
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(
    uiData: List<UserCartUiData>,
    modifier: Modifier = Modifier,
    onClickedBuyNowButton: () -> Unit,
    onProductClicked: (UserCartUiData) -> Unit,
    totalPrice: Double,
    onDecrement: (UserCartUiData) -> Unit,
    onIncrement: (UserCartUiData) -> Unit,
    updateTotalAmount: (List<UserCartUiData>) -> Unit,

) {
    Box(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiData.size) { cart ->
                CartItem(
                    cartUiData = uiData[cart],
                    onCartItemClicked = onProductClicked,
                    onIncrement = {
                        onIncrement(uiData[cart])
                        updateTotalAmount(uiData)
                    },
                    onDecrement = {
                        onDecrement(uiData[cart])
                        updateTotalAmount(uiData)
                    },
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
                        text = totalPrice.toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }

                Button(
                    onClick = { onClickedBuyNowButton() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Buy Now")
                }
            }
        }
    }
}