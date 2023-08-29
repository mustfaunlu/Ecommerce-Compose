package com.mustafaunlu.ecommerce_compose.ui.screens.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserInformationUiData
import com.mustafaunlu.ecommerce_compose.utils.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val readFirebaseUserInfosUseCase: ReadFirebaseUserInfosUseCase,
    private val sharedPreferences: SharedPreferences,
    private val firebaseUserInfoToUiData: ProductBaseMapper<UserInformationEntity, UserInformationUiData>,
) : ViewModel() {
    private val _userInfos = MutableLiveData<ScreenState<UserInformationUiData>>()
    val userInfos: LiveData<ScreenState<UserInformationUiData>> get() = _userInfos

    init {
        getUserInfosFromFirebase()
    }

    private fun getUserInfosFromFirebase() {
        _userInfos.value = ScreenState.Loading
        viewModelScope.launch {
            readFirebaseUserInfosUseCase.invoke(
                getUserIdFromSharedPref(sharedPreferences),
                onSuccess = {
                    _userInfos.postValue(ScreenState.Success(firebaseUserInfoToUiData.map(it)))
                },
            ) {
                _userInfos.postValue(ScreenState.Error(it))
            }
        }
    }
}
