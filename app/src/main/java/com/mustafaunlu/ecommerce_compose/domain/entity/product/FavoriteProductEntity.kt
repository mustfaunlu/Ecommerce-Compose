package com.mustafaunlu.ecommerce_compose.domain.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_items")
data class FavoriteProductEntity(
    val userId: String,
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: Int,
    @ColumnInfo(name = "product_price") val price: Int,
    @ColumnInfo(name = "product_quantity") val quantity: Int,
    @ColumnInfo(name = "product_title") val title: String,
    @ColumnInfo(name = "product_image") val image: String,
)
