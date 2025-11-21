package com.lyracore.zoho.core

import android.app.Application
import com.lyracore.zoho.core.interfaces.ExceptionHandlingCallback
import com.lyracore.zoho.core.models.ErrorEvent
import com.lyracore.zoho.core.models.ExceptionEvent
import com.lyracore.zoho.core.models.LyraConfig
import com.lyracore.zoho.core.models.ZohoConfig
import com.lyracore.zoho.core.models.enums.ErrorLocation
import com.lyracore.zoho.core.models.enums.ExceptionLocation
import com.zoho.commons.InitConfig
import com.zoho.livechat.android.listeners.InitListener
import com.zoho.salesiqembed.ZohoSalesIQ

object CoreInitializer {
    @Volatile private var initialized = false
    @Volatile private var zohoInitialized = false
    private var apiKey: String? = null
    private var config: LyraConfig? = null
    private var zohoAppKey: String? = null
    private var zohoAccessKey: String? = null
    private lateinit var exceptionHandlingCallback: ExceptionHandlingCallback

    internal fun initialize(config: LyraConfig) {
        if (!initialized) {
            synchronized(this) {
                if (!initialized) {
                    this.config = config
                    this.apiKey = config.apiKey
                    initialized = true
                }
            }
        }
    }

    /** Initialize Zoho-specific keys. This is called by ChatClient after successful Zoho init. */
    internal fun initializeZoho(application: Application, zohoConfig: ZohoConfig) {
        try {
            val initConfig = InitConfig()
            this.exceptionHandlingCallback = zohoConfig.exceptionHandlingCallback
            ZohoSalesIQ.init(
                    application,
                    zohoConfig.appKey,
                    zohoConfig.accessKey,
                    initConfig,
                    object : InitListener {
                        override fun onInitSuccess() {
                            ZohoSalesIQ.Chat.showOperatorImageInLauncher(false)
                            // Mark core as initialized with the keys
                            synchronized(this) {
                                if (!this@CoreInitializer.zohoInitialized) {
                                    this@CoreInitializer.zohoAppKey = zohoConfig.appKey
                                    this@CoreInitializer.zohoAccessKey = zohoConfig.accessKey
                                    this@CoreInitializer.zohoInitialized = true
                                }
                            }
                        }

                        override fun onInitError(errorCode: Int, errorMessage: String) {
                            // Handle initialization error
                            this@CoreInitializer.exceptionHandlingCallback.onError(
                                    ErrorEvent(
                                            errorCode,
                                            errorMessage,
                                            ErrorLocation.CORE_INITIALIZE
                                    )
                            )
                        }
                    }
            )
        } catch (ex: Exception) {
            // Handle exception
            this.exceptionHandlingCallback.onException(
                    ExceptionEvent(ex, ExceptionLocation.CORE_INITIALIZE)
            )
        }
    }

    internal fun isInitialized(): Boolean = initialized
    internal fun isZohoInitialized(): Boolean = zohoInitialized
    internal fun getApiKey(): String? = apiKey
    internal fun getConfig(): LyraConfig? = config
    internal fun getZohoAppKey(): String? = zohoAppKey
    internal fun getZohoAccessKey(): String? = zohoAccessKey
    internal fun getExceptionHandlingCallback(): ExceptionHandlingCallback = exceptionHandlingCallback

    /**
     * Reset the CoreInitializer state. This method is intended for testing purposes only.
     * @VisibleForTesting
     */
    internal fun reset() {
        synchronized(this) {
            initialized = false
            zohoInitialized = false
            apiKey = null
            config = null
            zohoAppKey = null
            zohoAccessKey = null
        }
    }
}
