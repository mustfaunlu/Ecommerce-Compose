package com.mustafaunlu.ecommerce_compose.domain.usecase.user.write_user

import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity

interface WriteFirebaseUserInfosUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )
}
