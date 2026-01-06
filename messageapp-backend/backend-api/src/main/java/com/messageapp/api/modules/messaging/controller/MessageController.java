package com.messageapp.api.modules.messaging.controller;

import com.messageapp.api.modules.messaging.dto.SendMessageRequest;
import com.messageapp.api.modules.messaging.model.Message;
import com.messageapp.api.modules.messaging.service.MessagingService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

	private final MessagingService messagingService;

	public MessagingController(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	// 1️⃣ SEND MESSAGE
	@PostMapping("/send")
	public void sendMessage(@RequestBody SendMessageRequest request) {

		messagingService.sendMessage(request.getConversationId(), request.getSenderId(), request.getContent());
	}

//	// 2️⃣ GET MESSAGES
//	@GetMapping("/{conversationId}")
//	public List<Message> getMessages(@PathVariable UUID conversationId) {
//		return messagingService.getMessagesByConversation(conversationId);
//	}

	// 2 pagition
		@GetMapping("/{conversationId}")
		public Page<Message> getMessages(@PathVariable UUID conversationId, @RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "20") int size) {
			return messagingService.getMessagesByConversation(conversationId, page, size);
		}
	// 3️⃣ MARK DELIVERED
	@PutMapping("/{conversationId}/delivered")
	public void markDelivered(@PathVariable UUID conversationId) {
		messagingService.markMessagesAsDelivered(conversationId);
	}

	// 4️⃣ MARK READ
	@PutMapping("/{conversationId}/read/{userId}")
	public void markRead(@PathVariable UUID conversationId, @PathVariable UUID userId) {
		messagingService.markMessagesAsRead(conversationId, userId);
	}
	
	// 5 unread
	
	@GetMapping("/{conversationId}/unread/{userId}")
	public long getUnreadCount(
	        @PathVariable UUID conversationId,
	        @PathVariable UUID userId
	) {
	    return messagingService.getUnreadCount(conversationId, userId);
	}


	
}
