package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.dto.UserPresenceDTO;
import org.springframework.stereotype.Service;

@Service
public class PresenceServiceImpl implements PresenceService {

    @Override
    public UserPresenceDTO userOnline(String userId) {
        return new UserPresenceDTO(userId, true, null);
    }

    @Override
    public UserPresenceDTO userOffline(String userId) {
        return new UserPresenceDTO(userId, false, null);
    }
}
