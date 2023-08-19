package com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge

import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartBadgeEntity
import kotlinx.coroutines.flow.Flow

interface UserCartBadgeUseCase {
    suspend operator fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>>

    suspend operator fun invoke(userCartBadgeEntity: UserCartBadgeEntity)
}