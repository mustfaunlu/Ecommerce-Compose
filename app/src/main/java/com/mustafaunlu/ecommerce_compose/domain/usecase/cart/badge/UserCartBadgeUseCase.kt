package com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge

import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface UserCartBadgeUseCase {
    suspend operator fun invoke(userId: String): Flow<NetworkResponseState<Int>>
}