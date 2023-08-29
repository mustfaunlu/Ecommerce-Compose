package com.mustafaunlu.ecommerce_compose.ui.screens.auth.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCase
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPwViewModel @Inject constructor(
    private val useCase: ForgotPwFirebaseUserUseCase,
): ViewModel() {
    private val _forgotPassword = MutableLiveData<ScreenState<String>>()
    val forgotPassword: LiveData<ScreenState<String>> get() = _forgotPassword

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPassword.postValue(ScreenState.Loading)
            useCase.invoke(
                email,
                onSuccess = {
                    _forgotPassword.postValue(ScreenState.Success(it))
                },
                onFailure = {
                    _forgotPassword.postValue(ScreenState.Error(it))
                },
            )
        }
    }
}