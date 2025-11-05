package com.lyracore.zoho.notification

import android.content.Context
import com.lyracore.zoho.core.CoreInitializer
import com.lyracore.zoho.core.models.ExceptionEvent
import com.lyracore.zoho.core.models.enums.ExceptionLocation
import com.zoho.livechat.android.ZohoLiveChat

object NotificationClient {
    /* Function that is used to enable push notifications from Zoho */
    fun enablePush(token: String, isTestDevice: Boolean) {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")
            if (!CoreInitializer.isZohoInitialized()) throw Exception("Zoho not initialized")

            ZohoLiveChat.Notification.enablePush(token, isTestDevice)
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                CoreInitializer.getExceptionHandlingCallback()
                        .onException(ExceptionEvent(ex, ExceptionLocation.NOTIFICATION_ENABLE_PUSH))
        }
    }

    /* Handles the notifications from Zoho? */
    fun handleNotification(context: Context, data: Map<Any?, Any?>) {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")
            if (!CoreInitializer.isZohoInitialized()) throw Exception("Zoho not initialized")

            ZohoLiveChat.Notification.handle(context, data)
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                CoreInitializer.getExceptionHandlingCallback()
                        .onException(
                                ExceptionEvent(ex, ExceptionLocation.NOTIFICATION_HANDLE_NOTIFICATION)
                        )
        }
    }

    /* Determines whether a notification is from Zoho. Returns null on exception */
    fun isZohoNotification(data: Map<Any?, Any?>): Boolean? {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")
            if (!CoreInitializer.isZohoInitialized()) throw Exception("Zoho not initialized")

            return ZohoLiveChat.Notification.isZohoSalesIQNotification(data)
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                CoreInitializer.getExceptionHandlingCallback()
                        .onException(
                                ExceptionEvent(ex, ExceptionLocation.NOTIFICATION_HANDLE_NOTIFICATION)
                        )
            return null
        }
    }
}
