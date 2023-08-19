package com.mustafaunlu.ecommerce_compose

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "FVM NEW TOKEN: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage.notification
        if (notification != null) {
            Log.d(TAG, "FCM TITLE: ${notification.title}")
            Log.d(TAG, "FCM BODY: ${notification.body}")
        }
    }

    companion object {
        private const val TAG = "AppFirebaseMessagingService"
    }
}
