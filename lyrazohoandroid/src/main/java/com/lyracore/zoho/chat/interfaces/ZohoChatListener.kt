package com.lyracore.zoho.chat.interfaces

import com.lyracore.zoho.chat.models.ChatEvent

/** Interface for listening to Zoho chat events. */
interface ZohoChatListener {
    fun onChatViewOpen(chatId: String) {}
    fun onChatViewClose(chatId: String) {}
    fun onChatOpened(chatEvent: ChatEvent) {}
    fun onChatClosed(chatEvent: ChatEvent) {}
    fun onChatAttended(chatEvent: ChatEvent) {}
    fun onChatMissed(chatEvent: ChatEvent) {}
    fun onChatReOpened(chatEvent: ChatEvent) {}
    fun onRating(chatEvent: ChatEvent) {}
    fun onFeedback(chatEvent: ChatEvent) {}
    fun onQueuePositionChange(chatEvent: ChatEvent) {}
}
