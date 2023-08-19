package com.mustafaunlu.ecommerce_compose.domain.usecase.cart // ktlint-disable package-name

import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity

interface UpdateCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
