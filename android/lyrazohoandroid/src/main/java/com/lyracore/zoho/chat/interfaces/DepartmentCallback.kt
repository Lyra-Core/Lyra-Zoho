package com.lyracore.zoho.chat.interfaces

import com.lyracore.zoho.chat.models.Department

/** Interface for department-related callbacks. */
interface DepartmentCallback {
    fun onSuccess(departments: List<Department>)
    fun onError(error: String)
}
