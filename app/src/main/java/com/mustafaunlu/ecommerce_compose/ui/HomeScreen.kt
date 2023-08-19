package com.mustafaunlu.ecommerce_compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.uiData.ProductUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.HomeViewModel
import com.mustafaunlu.ecommorce_develop.ui.theme.AppTheme

@Composable
fun HomeRoute(
    onProductClicked: (ProductUiData) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val productState by viewModel.products.observeAsState(initial = ScreenState.Loading)
    val categoryState by viewModel.categories.observeAsState(initial = ScreenState.Loading)
    val badgeState by viewModel.badge.observeAsState(initial = ScreenState.Loading)
    val onCategoryClicked = { category: String ->
        viewModel.getProductsByCategory(category)
    }
    HomeScreen(
        productState = productState,
        categoryState = categoryState,
        onProductClicked = onProductClicked,
        onCategoryClicked = onCategoryClicked,
    )
}

@Composable
fun HomeScreen(
    productState: ScreenState<List<ProductUiData>>?,
    categoryState: ScreenState<List<String>>,
    onProductClicked: (ProductUiData) -> Unit = {},
    onCategoryClicked: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            productState is ScreenState.Success && categoryState is ScreenState.Success -> {
                SuccessScreen(
                    productUiData = productState.uiData,
                    categoryUiData = categoryState.uiData,
                    onCategoryClicked = onCategoryClicked,
                    onProductClicked = onProductClicked,
                )
            }
            productState is ScreenState.Error || categoryState is ScreenState.Error -> {
                Error(message = R.string.error)
            }
            productState is ScreenState.Loading || categoryState is ScreenState.Loading -> {
                Loading()
            }
            else -> {
                Error(message = R.string.error)
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = "",
        onValueChange = { /* Handle search input change */ },
        label = { Text(text = stringResource(id = R.string.search_hint)) },
        modifier = modifier
            .fillMaxWidth().heightIn(min = 56.dp)
            .padding(16.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
    )
}

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    productUiData: List<ProductUiData>,
    categoryUiData: List<String>,
    onProductClicked: (ProductUiData) -> Unit = {},
    onCategoryClicked: (String) -> Unit,
) {
    Column(modifier = modifier) {
        SearchBar()
        CategoryList(
            categories = categoryUiData,
            onCategoryClicked = onCategoryClicked,
        )

        ProductList(
            products = productUiData,
            onProductClicked = onProductClicked,
        )
    }
}

@Preview
@Composable
fun LoadingItemPreview() {
    AppTheme {
        Loading()
    }
}

@Preview
@Composable
fun ErrorPreview() {
    AppTheme {
        Box {
            Error(R.string.error)
        }
    }
}
