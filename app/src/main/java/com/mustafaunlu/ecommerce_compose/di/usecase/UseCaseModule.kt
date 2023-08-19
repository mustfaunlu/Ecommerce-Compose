package com.mustafaunlu.ecommerce_compose.di.usecase

import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.CartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.CartUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.DeleteUserCartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.DeleteUserCartUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.UpdateCartUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.UpdateCartUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.cart.badge.UserCartBadgeUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.category.CategoryUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.category.CategoryUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.DeleteFavoriteUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.DeleteFavoriteUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.FavoriteUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.favorite.FavoriteUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.GetAllProductsUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.GetAllProductsUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.GetSingleProductUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.GetSingleProductUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.SearchProductUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.product.SearchProductUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_in.FirebaseUserSingInUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_in.FirebaseUserSingInUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_up.FirebaseUserSignUpUseCase
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.sign_up.FirebaseUserSignUpUseCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.write_user.WriteFirebaseUserInfosCaseImpl
import com.mustafaunlu.ecommerce_compose.domain.usecase.user.write_user.WriteFirebaseUserInfosUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProductsUseCase(
        getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl,
    ): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSingleProductUseCase(
        getSingleProductUseCaseImpl: GetSingleProductUseCaseImpl,
    ): GetSingleProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllCategoryUseCase(
        getAllCategoryUseCaseImpl: CategoryUseCaseImpl,
    ): CategoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindCartUseCase(
        cartUseCaseImpl: CartUseCaseImpl,
    ): CartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteUserCartUseCase(
        deleteUserCartUseCaseImpl: DeleteUserCartUseCaseImpl,
    ): DeleteUserCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchUseCase(
        searchUseCaseImpl: SearchProductUseCaseImpl,
    ): SearchProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateCartUseCase(
        updateCartUseCaseImpl: UpdateCartUseCaseImpl,
    ): UpdateCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteUseCase(
        favoriteUseCaseImpl: FavoriteUseCaseImpl,
    ): FavoriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFavoriteUseCase(
        deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl,
    ): DeleteFavoriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignUpUseCase(
        firebaseSignUpUseCaseImpl: FirebaseUserSignUpUseCaseImpl,
    ): FirebaseUserSignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignInUseCase(
        firebaseSignInUseCaseImpl: FirebaseUserSingInUseCaseImpl,
    ): FirebaseUserSingInUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseForgetPwUseCase(
        firebaseForgetPwUseCaseImpl: ForgotPwFirebaseUserUseCaseImpl,
    ): ForgotPwFirebaseUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseWriteUserUseCase(
        firebaseWriteUserUseCaseImpl: WriteFirebaseUserInfosCaseImpl,
    ): WriteFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseReadUserUseCase(
        firebaseReadUserCaseImpl: ReadFirebaseUserInfosUseCaseImpl,
    ): ReadFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUserCartBadgeUseCase(
        userCartBadgeUseCaseImpl: UserCartBadgeUseCaseImpl,
    ): UserCartBadgeUseCase
}
