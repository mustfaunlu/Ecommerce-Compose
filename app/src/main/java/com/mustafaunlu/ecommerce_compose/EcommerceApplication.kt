package com.mustafaunlu.ecommerce_compose

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class EcommerceApplication : Application() {

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()

       // val nightMode = sharedPrefs.getBoolean(Constants.PREF_THEME_KEY, false)
       // setAppTheme(nightMode)
    }

    private fun setAppTheme(nightMode: Boolean) {
        if (nightMode) {
           // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
