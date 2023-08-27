package com.mustafaunlu.ecommerce_compose.ui.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.DeleteUserCartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.UpdateCartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserCartUiData
import com.mustafaunlu.ecommerce_compose.utils.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val deleteCartUseCase: DeleteUserCartUseCase,
    private val mapper: ProductListMapper<UserCartEntity, UserCartUiData>,
    private val singleMapper: ProductBaseMapper<UserCartUiData, UserCartEntity>,
    private val badgeUseCase: UserCartBadgeUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    private val _userCarts = MutableLiveData<ScreenState<List<UserCartUiData>>>()
    val userCarts: LiveData<ScreenState<List<UserCartUiData>>> get() = _userCarts

    private val _totalPriceLiveData: MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPriceLiveData: LiveData<Double> get() = _totalPriceLiveData

    private val _badgeCountState = MutableStateFlow<Int>(value = 0)
    val badgeCount: StateFlow<Int> get() = _badgeCountState.asStateFlow()

    init {
        getCartsByUserId()
        getBadgeCount()
    }

    private fun getCartsByUserId() {
        viewModelScope.launch {
            cartUseCase(getUserIdFromSharedPref(sharedPreferences)).collect {
                when (it) {
                    is NetworkResponseState.Error -> _userCarts.postValue(ScreenState.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _userCarts.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> {
                        _userCarts.postValue(ScreenState.Success(mapper.map(it.result)))
                    }
                }
            }
        }
    }

    fun updateTotalPrice(cartList: List<UserCartUiData>) {
        viewModelScope.launch {
            _totalPriceLiveData.postValue(calculateTotalPrice(cartList))
            updateBadgeCount(cartList.size)
        }
    }

    fun updateBadgeCount(newCount: Int) {
        viewModelScope.launch {
            _badgeCountState.emit(newCount)
            _badgeCountState.value = newCount
        }
    }
    fun deleteUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch {
            deleteCartUseCase(singleMapper.map(userCartUiData))
            getCartsByUserId()
            getBadgeCount()
        }
    }
    fun updateUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch {
            updateCartUseCase(singleMapper.map(userCartUiData))
            getCartsByUserId()
        }
    }
    fun getBadgeCount() {
        viewModelScope.launch(Dispatchers.IO) {
            badgeUseCase(getUserIdFromSharedPref(sharedPreferences)).collectLatest {
                when (it) {
                    is NetworkResponseState.Error -> {}
                    is NetworkResponseState.Loading -> {}
                    is NetworkResponseState.Success -> {
                        _badgeCountState.emit(it.result)
                        _badgeCountState.value = it.result
                    }
                }
            }
        }
    }

    private fun calculateTotalPrice(cartList: List<UserCartUiData>): Double {
        var totalPrice = 0.0
        for (cart in cartList) {
            totalPrice += cart.price * cart.quantity
        }
        return totalPrice
    }
}
