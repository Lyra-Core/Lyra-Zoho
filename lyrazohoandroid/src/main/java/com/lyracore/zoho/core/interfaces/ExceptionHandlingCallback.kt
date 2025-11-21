package com.lyracore.zoho.core.interfaces

import com.lyracore.zoho.core.models.ErrorEvent
import com.lyracore.zoho.core.models.ExceptionEvent

/** Interface for error-related callbacks. */
interface ExceptionHandlingCallback {
    fun onError(error: ErrorEvent)
    fun onException(error: ExceptionEvent)
}
