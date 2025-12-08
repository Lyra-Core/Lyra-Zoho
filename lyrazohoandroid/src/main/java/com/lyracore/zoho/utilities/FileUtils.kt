package com.lyracore.zoho.utilities

import android.util.Log
import com.lyracore.zoho.core.models.enums.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object FileUtils {
    private val departmentCache = InMemoryCache<String, String>(100)

    suspend fun fetchDepartmentFile(environment: Environment): String {
        departmentCache.get("departments")?.let { return it }
        return withContext(Dispatchers.IO) {
            try {
                val fileUrl: String = if (environment == Environment.PRODUCTION) {
                    "https://icascontentstorage.blob.core.windows.net/assets/Chat/zoho-departments-Production.json"
                } else {
                    "https://icascontentstorage.blob.core.windows.net/assets/Chat/zoho-departments-Staging.json"
                }

                val url = URL(fileUrl)
                val uc: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                val br = BufferedReader(InputStreamReader(uc.getInputStream()))
                var line: String?
                val result = StringBuilder()
                while (br.readLine().also { line = it } != null) {
                    result.append(line)
                }

                val resultString = result.toString()

                departmentCache.let { departmentCache.put("departments", resultString) }
                resultString
            } catch (e: IOException) {
                Log.e("FileUtils", "Failed to fetch department file: ${e.localizedMessage}")
                e.printStackTrace()
                throw Exception(e)
            }
        }
    }

    fun clearCache() {
        departmentCache.remove("departments")
    }
}