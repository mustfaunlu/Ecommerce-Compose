package com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_in

import com.mustafaunlu.ecommerce_compose.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity

interface FirebaseUserSingInUseCase {
    operator fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}