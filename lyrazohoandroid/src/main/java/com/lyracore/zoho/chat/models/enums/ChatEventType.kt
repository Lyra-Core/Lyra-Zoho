package com.lyracore.zoho.chat.models.enums

/** Enum representing different types of events that can occur. */
enum class ChatEventType {
    CHAT_VIEW_OPEN,
    CHAT_VIEW_CLOSE,
    CHAT_OPENED,
    CHAT_CLOSED,
    CHAT_ATTENDED,
    CHAT_MISSED,
    CHAT_REOPENED,
    RATING,
    FEEDBACK,
    QUEUE_POSITION_CHANGE
}
