package com.mustafaunlu.ecommerce_compose.ui.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.ecommerce_compose.common.NetworkResponseState
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.DeleteFavoriteUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.FavoriteUseCase
import com.mustafaunlu.ecommerce_compose.ui.uiData.FavoriteUiData
import com.mustafaunlu.ecommerce_compose.utils.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val mapper: ProductListMapper<FavoriteProductEntity, FavoriteUiData>,
    private val singleMapper: ProductBaseMapper<FavoriteUiData, FavoriteProductEntity>,
    private val deleteCartUseCase: DeleteFavoriteUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    private val _favoriteCarts = MutableLiveData<ScreenState<List<FavoriteUiData>>>()
    val favoriteCarts: LiveData<ScreenState<List<FavoriteUiData>>> get() = _favoriteCarts

    init {
        getFavoriteProducts()
    }

    private fun getFavoriteProducts() {
        viewModelScope.launch {
            favoriteUseCase.invoke(getUserIdFromSharedPref(sharedPreferences)).collect {
                when (it) {
                    is NetworkResponseState.Error -> _favoriteCarts.postValue(
                        ScreenState.Error(
                            it.exception.message!!,
                        ),
                    )

                    is NetworkResponseState.Loading -> _favoriteCarts.postValue(ScreenState.Loading)
                    is NetworkResponseState.Success -> _favoriteCarts.postValue(
                        ScreenState.Success(
                            mapper.map(it.result),
                        ),
                    )
                }
            }
        }
    }

    fun deleteFavoriteItem(favoriteUiData: FavoriteUiData) {
        viewModelScope.launch {
            deleteCartUseCase(singleMapper.map(favoriteUiData))
        }
    }
}
