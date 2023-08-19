package com.mustafaunlu.ecommerce_compose.data.source.local

import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity

interface LocalDataSource {
    suspend fun getUserCartByUserIdFromDb(userId: String): List<UserCartEntity>

    suspend fun insertUserCartToDb(userCartEntity: UserCartEntity)

    suspend fun deleteUserCartFromDb(userCartEntity: UserCartEntity)

    suspend fun updateUserCartFromDb(userCartEntity: UserCartEntity)

    suspend fun getFavoriteProductsFromDb(userId: String): List<FavoriteProductEntity>

    suspend fun insertFavoriteItemToDb(favoriteProductEntity: FavoriteProductEntity)

    suspend fun deleteFavoriteItemFromDb(favoriteProductEntity: FavoriteProductEntity)

    suspend fun getUserCartBadgeStateFromDb(userUniqueInfo: String): UserCartBadgeEntity

    suspend fun insertUserCartBadgeCountToDb(userBadge: UserCartBadgeEntity)
}
