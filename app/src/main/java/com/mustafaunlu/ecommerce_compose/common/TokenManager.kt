package com.mustafaunlu.ecommerce_compose.common

import android.content.SharedPreferences
import android.util.Log
import arrow.core.getOrElse
import io.github.nefilim.kjwt.JWT
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sharedPref: SharedPreferences,
) {
    fun saveToken(token: String) {
        sharedPref.edit()
            .putString(Constants.USER_TOKEN, token)
            .putLong(Constants.USER_TOKEN_EXPIRATION_TIME, extractExpirationTimeFromToken(token))
            .apply()
    }

    fun getToken(): String? {
        return sharedPref.getString(Constants.USER_TOKEN, null)
    }

    private fun getTokenExpirationTime(): Long {
        return sharedPref.getLong(Constants.USER_TOKEN_EXPIRATION_TIME, 0)
    }

    fun deleteToken() {
        sharedPref.edit().remove(Constants.USER_TOKEN).apply()
        sharedPref.edit().remove(Constants.USER_TOKEN_EXPIRATION_TIME).apply()
    }

    fun isTokenValid(): Boolean {
        val expirationTime = getTokenExpirationTime()
        Log.d("TokenManager", "Token is valid: ${System.currentTimeMillis() / 1000 < expirationTime}")
        return (System.currentTimeMillis() / 1000) < expirationTime
    }

    private fun extractExpirationTimeFromToken(token: String) : Long {
        var expirationTime = 0L
        JWT.decode(
            token
        ).also {
            it.tap { decodedJWT ->
                expirationTime = decodedJWT.claimValueAsLong("exp").getOrElse { 0L }
                Log.d("TokenManager", "Expiration time: $expirationTime")
            }
        }
        return expirationTime
    }
}
