package com.mustafaunlu.ecommerce_compose.di.mappers

import com.mustafaunlu.ecommerce_compose.ui.uiData.ProductUiData
import com.mustafaunlu.ecommerce_compose.ui.uiData.FavoriteUiData
import com.mustafaunlu.ecommerce_compose.ui.uiData.DetailProductUiData
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserCartUiData
import com.mustafaunlu.ecommerce_compose.domain.entity.product.ProductEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.product.FavoriteProductEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.product.DetailProductEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.cart.UserCartEntity
import com.mustafaunlu.ecommerce_compose.domain.entity.user.UserInformationEntity
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductBaseMapper
import com.mustafaunlu.ecommerce_compose.domain.mapper.ProductListMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.CartEntityToUiMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.FavoriteEntityToUiMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.DetailProductEntityToUiMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.ProductEntityToUiMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.CartEntityToFavoriteEntityMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.CartUiToEntityMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.FavoriteUiToEntityMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.UserInfoEntityToUiDataMapper
import com.mustafaunlu.ecommerce_compose.ui.mapper.UserInfoUiDataToEntityMapper
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserInformationUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UiMapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindHomeProductUiMapper(productUiDataMapper: ProductEntityToUiMapper): ProductListMapper<ProductEntity, ProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindDetailProductUiMapper(productUiDataMapper: DetailProductEntityToUiMapper): ProductBaseMapper<DetailProductEntity, DetailProductUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindCartUiMapper(cartUiDataMapper: CartEntityToUiMapper): ProductListMapper<UserCartEntity, UserCartUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartUiMapper(singleCartUiDataMapper: CartUiToEntityMapper): ProductBaseMapper<UserCartUiData, UserCartEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoEntityToUiDataMapper(userInformationUiDataMapper: UserInfoEntityToUiDataMapper): ProductBaseMapper<UserInformationEntity, UserInformationUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindUserInfoUiDataToEntityMapper(userInformationEntityMapperToUi: UserInfoUiDataToEntityMapper): ProductBaseMapper<UserInformationUiData, UserInformationEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteItemUiMapper(favoriteEntityToUiMapper: FavoriteEntityToUiMapper): ProductListMapper<FavoriteProductEntity, FavoriteUiData>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleFavoriteItemUiMapper(favoriteUiToEntityMapper: FavoriteUiToEntityMapper): ProductBaseMapper<FavoriteUiData, FavoriteProductEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindSingleCartToFavoriteEntityMapper(cartEntityToFavoriteEntityMapper: CartEntityToFavoriteEntityMapper): ProductBaseMapper<UserCartEntity, FavoriteProductEntity>
}
