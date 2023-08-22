package com.mustafaunlu.ecommerce_compose.ui.mapper // ktlint-disable filename

import com.mustafaunlu.ecommerce_compose.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.ui.uiData.DetailProductUiData
import javax.inject.Inject

class DetailProductEntityToUiMapper @Inject constructor() :
    ProductBaseMapper<DetailProductEntity, DetailProductUiData> {
    override fun map(input: DetailProductEntity): DetailProductUiData {
        return DetailProductUiData(
            productId = input.id,
            title = input.title,
            description = input.description,
            price = input.price,
            imageUrl = input.imageUrl,
            rating = input.rating,
        )
    }
}
