package com.mustafaunlu.ecommerce_compose.ui.mapper

import com.mustafaunlu.ecommerce_compose.ui.uiData.ProductUiData
import com.mustafaunlu.ecommerce_compose.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductListMapper
import javax.inject.Inject

class ProductEntityToUiMapper @Inject constructor() :
    ProductListMapper<ProductEntity, ProductUiData> {
    override fun map(input: List<ProductEntity>): List<ProductUiData> {
        return input.map {
            ProductUiData(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                rating = it.rating,
            )
        }
    }
}
