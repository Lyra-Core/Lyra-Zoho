/** Data class representing visitor chat information. */
package com.lyracore.zoho.chat.models

data class VisitorChatData(
        val chatId: String,
        val question: String? = null,
        val attenderName: String? = null,
        val attenderEmail: String? = null,
        val attenderId: String? = null,
        val isBotAttender: Boolean = false,
        val departmentName: String? = null,
        val chatStatus: String? = null,
        val unreadCount: Int = 0,
        val feedback: String? = null,
        val rating: String? = null,
        val lastMessage: String? = null,
        val lastMessageTime: Long = 0,
        val lastMessageSender: String? = null,
        val queuePosition: Int = 0
)
