package com.messageapp.api.modules.messaging.controller;

import com.messageapp.api.modules.messaging.dto.ReactionCountDTO;
import com.messageapp.api.modules.messaging.dto.ReactionEvent;
import com.messageapp.api.modules.messaging.model.Reaction;
import com.messageapp.api.modules.messaging.service.ReactionService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Controller
public class ReactionController {

	private final ReactionService reactionService;
	private final SimpMessagingTemplate messagingTemplate;

	public ReactionController(ReactionService reactionService, SimpMessagingTemplate messagingTemplate) {
		this.reactionService = reactionService;
		this.messagingTemplate = messagingTemplate;
	}

	@MessageMapping("/chat.react")
	public void react(ReactionEvent event) {

		// 🔍 DEBUG LOG (VERY IMPORTANT)
		System.out.println("REACTION EVENT RECEIVED → " + event);

		// ✅ SAFETY CHECK (DO NOT TOUCH DB IF INVALID)
		if (event == null || event.getEmoji() == null || event.getMessageId() == null || event.getUserId() == null) {

			System.err.println("❌ INVALID REACTION PAYLOAD → " + event);
			return;
		}

		// ✅ CREATE REACTION ENTITY
		Reaction reaction = new Reaction();
		reaction.setId(UUID.randomUUID());
		reaction.setMessageId(event.getMessageId());
		reaction.setUserId(event.getUserId());
		reaction.setEmoji(event.getEmoji()); // 🔥 MUST NOT BE NULL
		reaction.setReactedAt(Instant.now());

		// 💾 SAVE TO DB
		reactionService.saveReaction(reaction);

		// 📊 FETCH UPDATED COUNTS
		List<ReactionCountDTO> counts = reactionService.getReactionCounts(event.getMessageId());

		// 📡 BROADCAST TO CLIENTS
		messagingTemplate.convertAndSend("/topic/reactions", counts);
	}
}
