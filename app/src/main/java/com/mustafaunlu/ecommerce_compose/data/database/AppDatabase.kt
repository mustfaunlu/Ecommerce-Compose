package com.mustafaunlu.ecommerce_compose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity

@Database(entities = [UserCartEntity::class, FavoriteProductEntity::class, UserCartBadgeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
