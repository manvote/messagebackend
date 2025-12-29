package com.messageapp.api.modules.messaging.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name = "message_reactions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"message_id", "user_id"})
    }
)
public class Reaction {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "message_id", nullable = false)
    private UUID messageId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;   // ✅ FIXED

    @Column(nullable = false)
    private String emoji;

    @Column(name = "reacted_at", nullable = false)
    private Instant reactedAt;

    // ---------- getters & setters ----------

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getUserId() {          // ✅ FIXED
        return userId;
    }

    public void setUserId(UUID userId) {   // ✅ FIXED
        this.userId = userId;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Instant getReactedAt() {
        return reactedAt;
    }

    public void setReactedAt(Instant reactedAt) {
        this.reactedAt = reactedAt;
    }
}
