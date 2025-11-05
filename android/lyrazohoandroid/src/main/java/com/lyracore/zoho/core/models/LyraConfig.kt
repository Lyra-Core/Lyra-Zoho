package com.lyracore.zoho.core.models

/** SDK configuration for initialization. */
data class LyraConfig(
        val apiKey: String,
        val baseUrl: String = "https://api.zoho.com",
        val timeoutMs: Long = 15_000
)
