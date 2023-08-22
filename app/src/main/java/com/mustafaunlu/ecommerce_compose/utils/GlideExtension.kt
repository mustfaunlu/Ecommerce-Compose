package com.mustafaunlu.ecommerce_compose.utils

import android.content.SharedPreferences
import com.mustafaunlu.ecommerce_compose.common.Constants

fun getUserIdFromSharedPref(sharedPrefs: SharedPreferences): String {
    return sharedPrefs.getString(
        Constants.PREF_FIREBASE_USERID_KEY,
        Constants.PREF_DEF_STR,
    ) ?: Constants.PREF_DEF_STR
}
