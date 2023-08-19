package com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_up

import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity

interface FirebaseUserSignUpUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )
}
