package com.mustafaunlu.ecommerce_compose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestinations {
    val route: String
}
object Home : AppDestinations {
    override val route = "home"
}

object ProductDetail : AppDestinations {
    override val route = "productDetail"

    private const val PRODUCT_ID = "productId"
    val routeWithArgs = "$route/{$PRODUCT_ID}"
    val arguments = listOf(
        navArgument(PRODUCT_ID) { type = NavType.IntType },
    )
}

object Splash : AppDestinations {
    override val route = "splash"
}

object Cart : AppDestinations {
    override val route = "cart"
}

object Profile : AppDestinations {
    override val route = "profile"
}

object SignIn : AppDestinations {
    override val route = "signIn"
}

object SignUp : AppDestinations {
    override val route = "signUp"
}

object Favorite : AppDestinations {
    override val route = "favorite"
}

object Payment : AppDestinations {
    override val route = "payment"
}
