package com.mustafaunlu.ecommerce_compose.ui.screens.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.ui.uiData.FavoriteUiData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteItem(
    favoriteUiData: FavoriteUiData,
    onProductClicked: (FavoriteUiData) -> Unit,
    onProductLongClicked: (FavoriteUiData) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .combinedClickable(
                onClick = { onProductClicked(favoriteUiData) },
                onLongClick = { onProductLongClicked(favoriteUiData) },
            ),
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
                model = favoriteUiData.imageUrl,
                contentDescription = null, // Provide content description
                modifier = Modifier
                    .size(60.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = favoriteUiData.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = favoriteUiData.price.toString())
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(modifier = Modifier.padding(15.dp), painter = painterResource(id = R.drawable.ic_favorite_filled), contentDescription = null)
        }
    }
}

@Composable
fun FavoriteList(
    favoriteUiData: List<FavoriteUiData>,
    onProductClicked: (FavoriteUiData) -> Unit,
    onProductLongClicked: (FavoriteUiData) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(favoriteUiData.size) { index ->
            FavoriteItem(favoriteUiData[index], onProductClicked = onProductClicked, onProductLongClicked = onProductLongClicked)
        }
    }
}
