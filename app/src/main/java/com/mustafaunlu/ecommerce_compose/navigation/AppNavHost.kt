package com.mustafaunlu.ecommerce_compose.navigation // ktlint-disable package-name

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mustafaunlu.ecommerce_compose.ui.cart.CartRoute
import com.mustafaunlu.ecommerce_compose.ui.detail.DetailRoute
import com.mustafaunlu.ecommerce_compose.ui.home.HomeRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
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
            CartRoute()
        }
        composable(Profile.route) {
            // ProfileRoute()
        }
        composable(SignIn.route) {
            // SignInRoute()
        }
        composable(SignUp.route) {
            // SignUpRoute()
        }
        composable(Favorite.route) {
            // FavoriteRoute()
        }
        composable(Payment.route) {
            // PaymentRoute()
        }
    }
}
