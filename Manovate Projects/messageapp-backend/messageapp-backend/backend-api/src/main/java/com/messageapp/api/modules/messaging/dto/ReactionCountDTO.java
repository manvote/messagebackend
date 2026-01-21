package com.messageapp.api.modules.messaging.dto;

public class ReactionCountDTO {

    private String emoji;
    private long count;

    public ReactionCountDTO(String emoji, long count) {
        this.emoji = emoji;
        this.count = count;
    }

    public String getEmoji() {
        return emoji;
    }

    public long getCount() {
        return count;
    }
}
