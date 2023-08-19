package com.mustafaunlu.ecommerce_compose.di.permission

import android.app.Activity
import com.mustafaunlu.ecommerce_compose.common.InternetPermissionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object PermissionModule {

    @Provides
    fun providePermissionManager(activity: Activity): InternetPermissionManager {
        return InternetPermissionManager(activity)
    }
}
