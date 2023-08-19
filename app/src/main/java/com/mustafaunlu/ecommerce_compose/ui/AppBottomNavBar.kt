package com.mustafaunlu.ecommerce_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafaunlu.ecommerce_compose.R

@Composable
fun AppBottomNavBar(modifier: Modifier = Modifier, badgeState: Int) {
    BottomNavigation(modifier, backgroundColor = MaterialTheme.colorScheme.outlineVariant) {
        BottomNavigationItem(selected = true, onClick = {}, icon = {
            Icon(imageVector = Icons.Default.Home, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }, label = {
            Text(stringResource(R.string.navbar_menu_home), color = MaterialTheme.colorScheme.primary)
        })
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            IconWithBadge(badge = badgeState, icon = Icons.Default.ShoppingCart, tint = MaterialTheme.colorScheme.primary)
        }, label = {
            Text(stringResource(R.string.navbar_menu_cart), color = MaterialTheme.colorScheme.primary)
        })
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }, label = {
            Text(stringResource(R.string.favorite), color = MaterialTheme.colorScheme.primary)
        })
        BottomNavigationItem(selected = false, onClick = {}, icon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }, label = {
            Text(stringResource(R.string.navbar_profile_cart), color = MaterialTheme.colorScheme.primary)
        })
    }
}

@Composable
fun IconWithBadge(badge: Int, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    Box(modifier = Modifier.size(36.dp)) {
        Icon(
            imageVector = icon,
            modifier = modifier.align(
                Alignment.BottomCenter,
            ),
            tint = tint,
            contentDescription = null,
        )

        if (badge != 0) {
            Text(
                text = "$badge",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.TopEnd)
                    .size(16.dp),
            )
        }
    }
}
