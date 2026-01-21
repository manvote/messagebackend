package com.messageapp.api.modules.messaging.controller;

import com.messageapp.api.modules.messaging.dto.ReactionCountDTO;
import com.messageapp.api.modules.messaging.dto.ReactionEvent;
import com.messageapp.api.modules.messaging.service.ReactionService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReactionController {

    private final ReactionService reactionService;
    private final SimpMessagingTemplate messagingTemplate;

    public ReactionController(
            ReactionService reactionService,
            SimpMessagingTemplate messagingTemplate) {
        this.reactionService = reactionService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.react")
    public void react(ReactionEvent event) {

        // 🔍 DEBUG LOG
        System.out.println("REACTION EVENT RECEIVED → " + event);

        // ✅ SAFETY CHECK
        if (event == null ||
            event.getEmoji() == null ||
            event.getMessageId() == null ||
            event.getUserId() == null) {

            System.err.println("❌ INVALID REACTION PAYLOAD → " + event);
            return;
        }

        // ✅ SAVE OR UPDATE REACTION (NO MANUAL ENTITY CREATION)
        reactionService.saveOrUpdateReaction(event);

        // 📊 FETCH UPDATED COUNTS
        List<ReactionCountDTO> counts =
                reactionService.getReactionCounts(event.getMessageId());

        // 📡 BROADCAST TO ALL SUBSCRIBERS
        messagingTemplate.convertAndSend("/topic/reactions", counts);
    }
}
