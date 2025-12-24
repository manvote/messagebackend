package com.messageapp.api.modules.messaging.dto;

import java.util.UUID;

public class ChatMessageRequest {

    private UUID conversationId;
    private UUID senderId;
    private String content;

    // getters & setters
    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
