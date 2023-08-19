package com.mustafaunlu.ecommerce_compose.domain.usecase.category

import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<String>>>
}
