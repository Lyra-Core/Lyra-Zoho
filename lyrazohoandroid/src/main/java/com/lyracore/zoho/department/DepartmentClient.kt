package com.lyracore.zoho.department

import android.content.Context
import com.lyracore.zoho.core.CoreInitializer
import com.lyracore.zoho.core.models.ExceptionEvent
import com.lyracore.zoho.core.models.enums.ExceptionLocation
import com.lyracore.zoho.department.models.Department
import java.io.InputStream
import kotlinx.serialization.json.Json

object DepartmentClient {
    internal fun getAllDepartments(context: Context): List<Department> {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")

            val inputStream: InputStream = context.assets.open("departments.json")
            val size: Int = inputStream.available()
            val buffer: ByteArray = ByteArray(size)
            inputStream.read(buffer)
            val jsonString: String = String(buffer, Charsets.UTF_8)
            return Json.decodeFromString<Array<Department>>(jsonString).toList()
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                    CoreInitializer.getExceptionHandlingCallback()
                            .onException(ExceptionEvent(ex, ExceptionLocation.DEPARTMENT_GET_ALL))
            return emptyList<Department>()
        }
    }

    internal fun getDefaultDepartment(context: Context): Department? {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")

            val inputStream: InputStream = context.assets.open("departments.json")
            val size: Int = inputStream.available()
            val buffer: ByteArray = ByteArray(size)
            inputStream.read(buffer)
            val jsonString: String = String(buffer, Charsets.UTF_8)
            val obj = Json.decodeFromString<Array<Department>>(jsonString)
            return obj.firstOrNull { o -> o.default }
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                    CoreInitializer.getExceptionHandlingCallback()
                            .onException(ExceptionEvent(ex, ExceptionLocation.DEPARTMENT_GET_DEFAULT))
            return null
        }
    }

    internal fun getDepartmentByCountry(context: Context, countryCode: String): Department? {
        try {
            if (!CoreInitializer.isInitialized()) throw Exception("SDK is not initialized")

            val inputStream: InputStream = context.assets.open("departments.json")
            val size: Int = inputStream.available()
            val buffer: ByteArray = ByteArray(size)
            inputStream.read(buffer)
            val jsonString: String = String(buffer, Charsets.UTF_8)
            val obj = Json.decodeFromString<List<Department>>(jsonString)
            return obj.firstOrNull() { o -> o.codes.contains(countryCode) }
        } catch (ex: Exception) {
            // Handle exception
            if (CoreInitializer.isInitialized())
                CoreInitializer.getExceptionHandlingCallback()
                        .onException(ExceptionEvent(ex, ExceptionLocation.DEPARTMENT_GET_BY_COUNTRY))
            return null
        }
    }
}
