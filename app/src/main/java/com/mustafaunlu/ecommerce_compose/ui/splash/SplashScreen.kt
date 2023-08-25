package com.mustafaunlu.ecommerce_compose.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafaunlu.ecommerce_compose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit,
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
        ),
        label = "",
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navigateToHomeScreen.invoke()
    }

    SplashDesign(alpha = alphaAnimation.value)
}

@Composable
fun SplashDesign(
    alpha: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .size(100.dp).alpha(alpha),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = stringResource(R.string.app_name),
        )
        Text(
            text = stringResource(R.string.splash_txt),
            style = MaterialTheme.typography.body1,
        )
    }
}
