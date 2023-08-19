package com.mustafaunlu.ecommerce_compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(name: String, onCategoryClicked: (String) -> Unit) {
    Card(
        modifier = Modifier.padding(10.dp).clickable { onCategoryClicked(name) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun CategoryList(categories: List<String>, onCategoryClicked: (String) -> Unit) {
    LazyRow {
        items(categories) { category ->
            CategoryItem(name = category, onCategoryClicked = onCategoryClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(name = "Category", onCategoryClicked = {})
}
