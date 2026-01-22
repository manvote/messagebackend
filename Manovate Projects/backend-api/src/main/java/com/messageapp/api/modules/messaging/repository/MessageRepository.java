package com.messageapp.api.modules.messaging.repository;

import com.messageapp.api.modules.messaging.model.Message;
import com.messageapp.api.modules.messaging.model.MessageStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

	// Existing (keep)
	List<Message> findByConversationId(UUID conversationId);

	List<Message> findByConversationIdAndStatus(UUID conversationId, MessageStatus status);

	// ✅ NEW — mark DELIVERED
	@Modifying
	@Query("""
			    UPDATE Message m
			    SET m.status = :status
			    WHERE m.conversationId = :conversationId
			    AND m.status = :currentStatus
			""")
	int updateStatusByConversation(@Param("conversationId") UUID conversationId,
			@Param("currentStatus") MessageStatus currentStatus, @Param("status") MessageStatus status);

	// ✅ NEW — mark READ
	@Modifying
	@Query("""
			    UPDATE Message m
			    SET m.status = 'READ'
			    WHERE m.conversationId = :conversationId
			    AND m.senderId <> :userId
			    AND m.status <> 'READ'
			""")
	int markMessagesAsRead(@Param("conversationId") UUID conversationId, @Param("userId") UUID userId);
    
	
	// pagition 
	Page<Message> findByConversationIdOrderByCreatedAtDesc(UUID conversationId, Pageable pageable);
	
	// 🔥 NEW: Unread count
    @Query("""
        SELECT COUNT(m)
        FROM Message m
        WHERE m.conversationId = :conversationId
          AND m.senderId <> :userId
          AND m.status <> :readStatus
    """)
    long countUnreadMessages(
            @Param("conversationId") UUID conversationId,
            @Param("userId") UUID userId,
            @Param("readStatus") MessageStatus readStatus
    );
	

}
