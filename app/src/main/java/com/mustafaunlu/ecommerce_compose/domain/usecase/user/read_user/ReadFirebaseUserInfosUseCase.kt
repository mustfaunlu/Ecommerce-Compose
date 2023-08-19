package com.mustafaunlu.ecommerce_compose.domain.usecase.user.read_user

import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity

interface ReadFirebaseUserInfosUseCase {
    operator fun invoke(
        userId: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit,
    )
}
