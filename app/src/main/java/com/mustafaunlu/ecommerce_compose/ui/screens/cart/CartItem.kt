package com.mustafaunlu.ecommerce_compose.ui.screens.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserCartUiData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartItem(
    cartUiData: UserCartUiData,
    onCartItemClicked: (UserCartUiData) -> Unit,
    onCartLongClicked: (UserCartUiData) -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = { onCartItemClicked(cartUiData) },
                onLongClick = {
                    onCartLongClicked(cartUiData)
                },
            )
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            AsyncImage(
                model = cartUiData.imageUrl,
                contentDescription = stringResource(id = R.string.product_image_content), // Provide content description
                modifier = Modifier
                    .size(60.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    modifier = Modifier
                        .widthIn(max = 150.dp),
                    text = cartUiData.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = cartUiData.price.toString())
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "Increment",
                    modifier = Modifier.clickable { onIncrement() },
                )
                Text(text = cartUiData.quantity.toString(), fontWeight = FontWeight.Bold)
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = "Decrement",
                    modifier = Modifier.clickable { onDecrement() },
                )
            }
        }
    }
}
