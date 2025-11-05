package com.lyracore.zoho.core.models

import com.lyracore.zoho.core.models.enums.ErrorLocation

/** Data class representing a error event with associated error. */
data class ErrorEvent(
        val errorCode: Int,
        val errorMessage: String,
        val errorLocation: ErrorLocation
)
