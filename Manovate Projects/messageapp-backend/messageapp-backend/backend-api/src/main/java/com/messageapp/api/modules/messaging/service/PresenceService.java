//
//package com.messageapp.api.modules.messaging.service;
//
//import com.messageapp.api.modules.messaging.dto.UserPresenceDTO;
//import com.messageapp.api.modules.messaging.service.PresenceService;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class PresenceService {
//
//    private final PresenceService presenceService;
//
//    public PresenceService(PresenceService presenceService) {
//        this.presenceService = presenceService;
//    }
//
//    // user comes online
//    @MessageMapping("/presence/online")
//    @SendTo("/topic/presence")
//    public UserPresenceDTO online(String userId) {
//        return presenceService.userOnline(userId);
//    }
//
//    public UserPresenceDTO userOnline(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	// user goes offline
//    @MessageMapping("/presence/offline")
//    @SendTo("/topic/presence")
//    public UserPresenceDTO offline(String userId) {
//        return presenceService.userOffline(userId);
//    }
//
//	public UserPresenceDTO userOffline(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.dto.UserPresenceDTO;

public interface PresenceService {

    UserPresenceDTO userOnline(String userId);

    UserPresenceDTO userOffline(String userId);
}

