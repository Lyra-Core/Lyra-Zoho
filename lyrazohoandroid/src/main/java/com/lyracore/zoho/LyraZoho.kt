package com.lyracore.zoho

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.lyracore.zoho.chat.ChatClient
import com.lyracore.zoho.chat.interfaces.ZohoChatListener
import com.lyracore.zoho.chat.models.ChatAdditionalInformation
import com.lyracore.zoho.core.CoreInitializer
import com.lyracore.zoho.core.models.ZohoConfig
import com.lyracore.zoho.core.models.enums.Environment
import com.lyracore.zoho.department.DepartmentClient
import com.lyracore.zoho.notification.NotificationClient

/**
 * Enhanced LyraZoho SDK with comprehensive chat functionality. Professional Zoho SalesIQ
 * integration for Android applications.
 */
object LyraZoho {
    /** Initialize the SDK with full configuration. */
    fun initialize(application: Application, zohoConfig: ZohoConfig) {
        CoreInitializer.initializeZoho(application, zohoConfig)
    }

    // Basic SDK methods
    fun isZohoInitialized(): Boolean = CoreInitializer.isZohoInitialized()

    /**
     * Sets the current environment for the LyraZoho SDK.
     *
     * This method allows you to switch between different environments (e.g., PRODUCTION, STAGING)
     * for the SDK. It should be called after initializing the SDK with [initialize], and before performing
     * any operations that depend on the environment.
     *
     * @param environment The [Environment] to set for the SDK.
     *
     * @see initialize
     * @see com.lyracore.zoho.core.models.enums.Environment
     *
     * @sample
     * ```
     * LyraZoho.initialize(application, zohoConfig)
     * LyraZoho.setEnvironment(Environment.STAGING)
     * ```
     */
    fun setEnvironment(environment: Environment) = CoreInitializer.setEnvironment(environment)

    // Chat functionality - properly exposed at the SDK level
    fun startChatListeners(listener: ZohoChatListener? = null) = ChatClient.startListeners(listener)
    fun openChat(activity: AppCompatActivity) = ChatClient.open(activity)
    suspend fun setChatDepartment(departmentName: String) =
        ChatClient.setDepartment(departmentName)

    fun setChatLanguage(context: Context, languageCode: String) = ChatClient.setLanguage(context, languageCode)
    fun setChatQuestion(context: Context) = ChatClient.setQuestion(context)
    fun setPageTitle(title: String) = ChatClient.setPageTitle(title)
    fun setAdditionalInformation(additionalInfo: ChatAdditionalInformation) =
        ChatClient.setAdditionalInformation(additionalInfo)

    fun endChatSession(application: Application) = ChatClient.endSession(application)

    fun showZohoLauncher() = ChatClient.showZohoLauncher()

    // Notification functionality
    fun enablePushNotification(token: String, isTestDevice: Boolean) =
        NotificationClient.enablePush(token, isTestDevice)

    fun handlePushNotification(context: Context, data: Map<Any?, Any?>) =
        NotificationClient.handleNotification(context, data)

    fun isZohoPushNotification(data: Map<Any?, Any?>) = NotificationClient.isZohoNotification(data)

    // Department functionality
    suspend fun getAllDepartments() = DepartmentClient.getAllDepartments()
    suspend fun getDefaultDepartment() = DepartmentClient.getDefaultDepartment()
    suspend fun getDepartmentsByCountryCode(countryCode: String) =
        DepartmentClient.getDepartmentByCountry(countryCode)
}
