package com.messageapp.api.modules.messaging.repository;

import com.messageapp.api.modules.messaging.model.ConversationMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, UUID> {

    List<ConversationMember> findByConversationId(UUID conversationId);

    List<ConversationMember> findByUserId(UUID userId);
}
