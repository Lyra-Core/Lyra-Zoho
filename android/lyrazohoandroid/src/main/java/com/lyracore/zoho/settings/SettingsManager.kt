package com.lyracore.zoho.settings

object SettingsManager {
    data class Settings(val version: Int = 1)
    fun fetch(): Settings = Settings()
}
