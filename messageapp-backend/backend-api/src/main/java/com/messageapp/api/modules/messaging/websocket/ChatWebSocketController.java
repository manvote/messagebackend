package com.messageapp.api.modules.messaging.websocket;

import com.messageapp.api.modules.messaging.dto.ChatMessageRequest;
import com.messageapp.api.modules.messaging.model.Message;
import com.messageapp.api.modules.messaging.model.MessageStatus;
import com.messageapp.api.modules.messaging.repository.MessageRepository;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final MessageRepository messageRepository;

    public ChatWebSocketController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/conversation")
    public Message sendMessage(ChatMessageRequest request) {

        Message message = new Message();
        message.setConversationId(request.getConversationId());
        message.setSenderId(request.getSenderId());
        message.setContent(request.getContent());
        message.setStatus(MessageStatus.SENT);

        // save to DB (your existing logic)
        messageRepository.save(message);

        // return → broadcast to all subscribers
        return message;
    }
}

