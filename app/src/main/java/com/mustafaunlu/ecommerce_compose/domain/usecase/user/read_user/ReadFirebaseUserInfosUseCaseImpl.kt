package com.mustafaunlu.ecommerce_compose.domain.usecase.user.read_user

import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce_compose.domain.repository.FirebaseRepository
import javax.inject.Inject

class ReadFirebaseUserInfosUseCaseImpl @Inject constructor(
    private val repository: FirebaseRepository
): ReadFirebaseUserInfosUseCase {
    override fun invoke(
        userId: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repository.readUserFromFirebaseDatabase(userId, onSuccess, onFailure)
    }
}