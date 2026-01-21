package com.messageapp.api.modules.messaging.repository;

import com.messageapp.api.modules.messaging.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
}
