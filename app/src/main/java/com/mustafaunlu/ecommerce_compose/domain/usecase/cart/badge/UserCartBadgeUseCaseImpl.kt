package com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge

import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce_compose.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCartBadgeUseCaseImpl @Inject constructor(
    private val repository: LocalRepository
) : UserCartBadgeUseCase {
    override suspend fun invoke(userUniqueInfo: String): Flow<NetworkResponseState<UserCartBadgeEntity>> {
        return repository.getUserCartBadgeStateFromDb(userUniqueInfo)
    }

    override suspend fun invoke(userCartBadgeEntity: UserCartBadgeEntity) {
        repository.insertUserCartBadgeStateToDb(userCartBadgeEntity)
    }
}