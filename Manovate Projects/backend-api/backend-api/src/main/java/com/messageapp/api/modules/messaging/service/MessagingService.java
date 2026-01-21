package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.model.Message;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

public interface MessagingService {

	void sendMessage(UUID conversationId, UUID senderId, String content);

	List<Message> getMessagesByConversation(UUID conversationId);

	void markMessagesAsDelivered(UUID conversationId);

	void markMessagesAsRead(UUID conversationId, UUID userId);

	Page<Message> getMessagesByConversation(UUID conversationId, int page, int size);
	
	long getUnreadCount(UUID conversationId, UUID userId);
}
