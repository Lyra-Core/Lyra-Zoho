package com.lyracore.zoho.department.models

import kotlinx.serialization.Serializable

@Serializable
data class Department(val name: String, val codes: List<String>, val default: Boolean)
