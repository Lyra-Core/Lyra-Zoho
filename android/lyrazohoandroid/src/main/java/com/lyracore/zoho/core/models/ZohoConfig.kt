package com.lyracore.zoho.core.models

import com.lyracore.zoho.core.interfaces.ExceptionHandlingCallback

/** Zoho SDK configuration for initialization. */
open class ZohoConfig(
        val appKey: String,
        val accessKey: String,
        val exceptionHandlingCallback: ExceptionHandlingCallback
)
