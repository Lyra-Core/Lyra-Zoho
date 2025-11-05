package com.lyracore.zoho.chat.models

import com.lyracore.zoho.chat.models.enums.ChatEventType

/** Data class representing a chat event with associated data. */
data class ChatEvent(
        val type: ChatEventType,
        val chatId: String? = null,
        val visitorChat: VisitorChatData? = null
)
