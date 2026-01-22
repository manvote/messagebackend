package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.model.Message;
import com.messageapp.api.modules.messaging.model.MessageStatus;
import com.messageapp.api.modules.messaging.repository.MessageRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MessagingServiceImpl implements MessagingService {

	private final MessageRepository messageRepository;

	public MessagingServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	// 1️⃣ SEND MESSAGE
	@Override
	public void sendMessage(UUID conversationId, UUID senderId, String content) {

		Message message = new Message();
		message.setConversationId(conversationId);
		message.setSenderId(senderId);
		message.setContent(content);
		message.setStatus(MessageStatus.SENT);

		messageRepository.save(message);
	}

	// 2️⃣ GET MESSAGES
	@Override
	public List<Message> getMessagesByConversation(UUID conversationId) {
		return messageRepository.findByConversationId(conversationId);
	}

	// 3️⃣ MARK AS DELIVERED
	@Override
	public void markMessagesAsDelivered(UUID conversationId) {

		List<Message> messages = messageRepository.findByConversationIdAndStatus(conversationId, MessageStatus.SENT);

		for (Message message : messages) {
			message.setStatus(MessageStatus.DELIVERED);
		}

		messageRepository.saveAll(messages);
	}

	// 4️⃣ MARK AS READ
	@Override
	public void markMessagesAsRead(UUID conversationId, UUID userId) {

		List<Message> messages = messageRepository.findByConversationIdAndStatus(conversationId,
				MessageStatus.DELIVERED);

		for (Message message : messages) {
			// sender should NOT mark own message as READ
			if (!message.getSenderId().equals(userId)) {
				message.setStatus(MessageStatus.READ);
			}
		}

		messageRepository.saveAll(messages);
	}

	// pagition
	@Override
	public Page<Message> getMessagesByConversation(UUID conversationId, int page, int size) {

		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

		return messageRepository.findByConversationIdOrderByCreatedAtDesc(conversationId, pageable);
	}
	
	// unread 

	@Override
	public long getUnreadCount(UUID conversationId, UUID userId) {
		
		 return messageRepository.countUnreadMessages(
		            conversationId,
		            userId,
		            MessageStatus.READ
		    );
	}
}
