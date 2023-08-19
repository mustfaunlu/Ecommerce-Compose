package com.mustafaunlu.ecommerce_compose.utils

import android.content.SharedPreferences
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mustafaunlu.ecommerce_compose.common.Constants

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun getUserIdFromSharedPref(sharedPrefs: SharedPreferences): String {
    return sharedPrefs.getString(
        Constants.PREF_FIREBASE_USERID_KEY,
        Constants.PREF_DEF_STR,
    ) ?: Constants.PREF_DEF_STR
}
