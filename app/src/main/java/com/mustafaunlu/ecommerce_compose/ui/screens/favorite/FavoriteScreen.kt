package com.mustafaunlu.ecommerce_compose.ui.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.Loading
import com.mustafaunlu.ecommerce_compose.ui.uiData.FavoriteUiData

@Composable
fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onProductClicked: (FavoriteUiData) -> Unit,
) {
    val favoriteState by viewModel.favoriteCarts.observeAsState(initial = ScreenState.Loading)
    val onLongClicked: (FavoriteUiData) -> Unit = { favoriteUiData ->
        viewModel.deleteFavoriteItem(favoriteUiData)
    }
    FavoriteScreen(
        favoriteState = favoriteState,
        onProductClicked = onProductClicked,
        onProductLongClicked = onLongClicked,

    )
}

@Composable
fun FavoriteScreen(
    favoriteState: ScreenState<List<FavoriteUiData>>?,
    onProductClicked: (FavoriteUiData) -> Unit,
    onProductLongClicked: (FavoriteUiData) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (favoriteState) {
            ScreenState.Loading -> {
                Loading()
            }
            is ScreenState.Error -> {
                Error(message = favoriteState.message)
            }
            is ScreenState.Success -> {
                FavoriteList(favoriteUiData = favoriteState.uiData, onProductClicked = onProductClicked, onProductLongClicked = onProductLongClicked)
            }

            else -> {}
        }
    }
}
