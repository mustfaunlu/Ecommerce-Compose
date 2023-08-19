package com.mustafaunlu.ecommerce_compose.ui.mapper

import com.mustafaunlu.ecommerce_compose.ui.uiData.FavoriteUiData
import com.mustafaunlu.ecommerce_compose.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductListMapper
import javax.inject.Inject

class FavoriteEntityToUiMapper @Inject constructor() :
    ProductListMapper<FavoriteProductEntity, FavoriteUiData> {
    override fun map(input: List<FavoriteProductEntity>): List<FavoriteUiData> {
        return input.map {
            FavoriteUiData(
                userId = it.userId,
                productId = it.productId,
                price = it.price,
                quantity = it.quantity,
                title = it.title,
                imageUrl = it.image,
            )
        }
    }
}

class FavoriteUiToEntityMapper @Inject constructor() :
    ProductBaseMapper<FavoriteUiData, FavoriteProductEntity> {
    override fun map(input: FavoriteUiData): FavoriteProductEntity {
        return FavoriteProductEntity(
            userId = input.userId,
            productId = input.productId,
            price = input.price,
            quantity = input.quantity,
            title = input.title,
            image = input.imageUrl,
        )
    }
}