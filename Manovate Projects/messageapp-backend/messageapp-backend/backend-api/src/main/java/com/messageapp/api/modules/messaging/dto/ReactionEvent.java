package com.messageapp.api.modules.messaging.dto;

import java.util.UUID;

public class ReactionEvent {

    private UUID messageId;
    private UUID userId;   // ✅ FIXED
    private String emoji;

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getUserId() {          // ✅ FIXED
        return userId;
    }

    public void setUserId(UUID userId) {   // ✅ FIXED
        this.userId = userId;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
