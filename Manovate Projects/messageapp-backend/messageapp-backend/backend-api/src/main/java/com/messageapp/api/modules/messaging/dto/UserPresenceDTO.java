package com.messageapp.api.modules.messaging.dto;

import java.time.Instant;

public class UserPresenceDTO {

    private String userId;
    private boolean online;
    private Instant lastSeen;

    public UserPresenceDTO(String userId, boolean online, Instant lastSeen) {
        this.userId = userId;
        this.online = online;
        this.lastSeen = lastSeen;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isOnline() {
        return online;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }
}
