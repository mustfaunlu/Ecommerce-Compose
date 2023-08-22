package com.mustafaunlu.ecommerce_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mustafaunlu.ecommerce_compose.navigation.AppBottomNavBar
import com.mustafaunlu.ecommerce_compose.navigation.AppNavHost
import com.mustafaunlu.ecommerce_compose.navigation.SignIn
import com.mustafaunlu.ecommerce_compose.navigation.SignUp
import com.mustafaunlu.ecommorce_develop.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    AppTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val navController = rememberNavController()
            val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            when (navBackStackEntry?.destination?.route) {
                SignIn.route -> bottomBarState.value = false
                SignUp.route -> bottomBarState.value = false
                else -> bottomBarState.value = true
            }

            Scaffold(
                bottomBar = { AppBottomNavBar(badgeState = 1, navController = navController, bottomBarState = bottomBarState) },
            ) { paddingValues ->
                AppNavHost(navController = navController, modifier = Modifier.padding(paddingValues))
            }
        }
    }
}
