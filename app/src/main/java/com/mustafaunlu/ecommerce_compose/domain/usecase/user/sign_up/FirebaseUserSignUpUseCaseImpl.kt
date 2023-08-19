package com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_up

import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce_compose.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseUserSignUpUseCaseImpl @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) : FirebaseUserSignUpUseCase {
    override fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        firebaseRepository.signUpWithFirebase(user, onSuccess, onFailure)
    }
}
