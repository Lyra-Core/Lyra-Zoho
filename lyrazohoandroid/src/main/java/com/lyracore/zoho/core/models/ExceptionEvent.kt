package com.lyracore.zoho.core.models

import com.lyracore.zoho.core.models.enums.ExceptionLocation

/** Data class representing a error event with associated error. */
open class ExceptionEvent(val exception: Exception, val exceptionLocation: ExceptionLocation)
