package com.messageapp.api.modules.messaging.controller;

import com.messageapp.api.modules.messaging.dto.TypingEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TypingController {

    // Client sends to: /app/typing
    // Server broadcasts to: /topic/typing/{conversationId}

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public TypingEvent typing(TypingEvent event) {
        return event;
    }
}
