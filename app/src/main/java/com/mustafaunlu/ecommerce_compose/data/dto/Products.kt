package com.mustafaunlu.ecommerce_compose.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Products(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "products")
    val products: List<Product>,
    @Json(name = "skip")
    val skip: Int,
    @Json(name = "total")
    val total: Int,
)
