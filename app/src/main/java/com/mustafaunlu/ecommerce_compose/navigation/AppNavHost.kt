package com.mustafaunlu.ecommerce_compose.navigation // ktlint-disable package-name

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mustafaunlu.ecommerce_compose.ui.auth.SignInRoute
import com.mustafaunlu.ecommerce_compose.ui.auth.SignUpRoute
import com.mustafaunlu.ecommerce_compose.ui.cart.CartRoute
import com.mustafaunlu.ecommerce_compose.ui.detail.DetailRoute
import com.mustafaunlu.ecommerce_compose.ui.favorite.FavoriteRoute
import com.mustafaunlu.ecommerce_compose.ui.home.HomeRoute
import com.mustafaunlu.ecommerce_compose.ui.payment.PaymentRoute
import com.mustafaunlu.ecommerce_compose.ui.profile.ProfileRoute
import com.mustafaunlu.ecommerce_compose.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Splash.route, modifier = modifier) {
        composable(Splash.route) {
            SplashScreen(
                navigateToHomeScreen = {
                    navController.navigate(SignIn.route)
                },
            )
        }

        composable(Home.route) {
            HomeRoute(
                onProductClicked = {
                    val route = "${ProductDetail.route}/${it.id}"
                    navController.navigate(route = route)
                },
            )
        }
        composable(ProductDetail.routeWithArgs, arguments = ProductDetail.arguments) {
            DetailRoute()
        }
        composable(Cart.route) {
            CartRoute(
                onClickedBuyNowButton = {
                    navController.navigate(Payment.route)
                },
                onProductClicked = {
                    val route = "${ProductDetail.route}/${it.productId}"
                    navController.navigate(route = route)
                },
            )
        }
        composable(Profile.route) {
            ProfileRoute(
                logout = {
                    navController.navigate(SignIn.route)
                },
            )
        }
        composable(SignIn.route) {
            SignInRoute(
                onGoSignUpButtonClicked = {
                    navController.navigate(SignUp.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(Home.route)
                },
            )
        }
        composable(SignUp.route) {
            SignUpRoute(
                navigateToSignInScreen = {
                    navController.navigate(SignIn.route)
                },
            )
        }
        composable(Favorite.route) {
            FavoriteRoute(
                onProductClicked = {
                    val route = "${ProductDetail.route}/${it.productId}"
                    navController.navigate(route = route)
                },
            )
        }
        composable(Payment.route) {
            PaymentRoute()
        }
    }
}
