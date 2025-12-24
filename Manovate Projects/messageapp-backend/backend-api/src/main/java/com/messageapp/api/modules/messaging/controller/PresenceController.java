package com.messageapp.api.modules.messaging.controller;

import com.messageapp.api.modules.messaging.dto.UserPresenceDTO;
import com.messageapp.api.modules.messaging.service.PresenceService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PresenceController {

    private final PresenceService presenceService;

    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // user comes online
    @MessageMapping("/presence/online")
    @SendTo("/topic/presence")
    public UserPresenceDTO online(String userId) {
        return presenceService.userOnline(userId);
    }

    // user goes offline
    @MessageMapping("/presence/offline")
    @SendTo("/topic/presence")
    public UserPresenceDTO offline(String userId) {
        return presenceService.userOffline(userId);
    }
}
