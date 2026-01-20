package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.model.Message;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MessagingService {

    // SEND MESSAGE
    void sendMessage(UUID conversationId, UUID senderId, String content);

    // GET MESSAGES (NON PAGED)
    List<Message> getMessagesByConversation(UUID conversationId);

    // GET MESSAGES (PAGED)
    Page<Message> getMessagesByConversation(UUID conversationId, int page, int size);

    // MARK DELIVERED
    void markMessagesAsDelivered(UUID conversationId);

    // MARK READ
    void markMessagesAsRead(UUID conversationId, UUID userId);

    // UNREAD COUNT
    long getUnreadCount(UUID conversationId, UUID userId);
}
