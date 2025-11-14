package com.lyracore.zoho

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.lyracore.zoho.chat.ChatClient
import com.lyracore.zoho.chat.interfaces.ZohoChatListener
import com.lyracore.zoho.chat.models.ChatAdditionalInformation
import com.lyracore.zoho.core.CoreInitializer
import com.lyracore.zoho.core.models.LyraConfig
import com.lyracore.zoho.core.models.ZohoConfig
import com.lyracore.zoho.department.DepartmentClient
import com.lyracore.zoho.notification.NotificationClient

/**
 * Enhanced LyraZoho SDK with comprehensive chat functionality. Professional Zoho SalesIQ
 * integration for Android applications.
 */
object LyraZoho {
    /** Initialize the SDK with full configuration. */
    fun initialize(application: Application, config: LyraConfig, zohoConfig: ZohoConfig) {
        CoreInitializer.initialize(config)
        CoreInitializer.initializeZoho(application, zohoConfig)
    }

    // Basic SDK methods
    fun isInitialized(): Boolean = CoreInitializer.isInitialized()
    fun isZohoInitialized(): Boolean = CoreInitializer.isZohoInitialized()
    fun getApiKey(): String? = CoreInitializer.getApiKey()
    fun getConfig(): LyraConfig? = CoreInitializer.getConfig()

    // Chat functionality - properly exposed at the SDK level
    fun startChatListeners(listener: ZohoChatListener? = null) = ChatClient.startListeners(listener)
    fun openChat(activity: AppCompatActivity) = ChatClient.open(activity)
    fun setChatDepartment(context: Context, departmentName: String) =
        ChatClient.setDepartment(context, departmentName)

    fun setChatLanguage(context: Context, languageCode: String) = ChatClient.setLanguage(context, languageCode)
    fun setChatQuestion(context: Context) = ChatClient.setQuestion(context)
    fun setPageTitle(title: String) = ChatClient.setPageTitle(title)
    fun setAdditionalInformation(additionalInfo: ChatAdditionalInformation) =
        ChatClient.setAdditionalInformation(additionalInfo)

    fun endChatSession(application: Application) = ChatClient.endSession(application)

    // Notification functionality
    fun enablePushNotification(token: String, isTestDevice: Boolean) =
        NotificationClient.enablePush(token, isTestDevice)

    fun handlePushNotification(context: Context, data: Map<Any?, Any?>) =
        NotificationClient.handleNotification(context, data)

    fun isZohoPushNotification(data: Map<Any?, Any?>) = NotificationClient.isZohoNotification(data)

    // Department functionality
    fun getAllDepartments(context: Context) = DepartmentClient.getAllDepartments(context)
    fun getDefaultDepartment(context: Context) = DepartmentClient.getDefaultDepartment(context)
    fun getDepartmentsByCountryCode(context: Context, countryCode: String) =
        DepartmentClient.getDepartmentByCountry(context, countryCode)
}
