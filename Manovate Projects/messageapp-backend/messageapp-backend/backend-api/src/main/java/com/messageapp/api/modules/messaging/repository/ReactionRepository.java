package com.messageapp.api.modules.messaging.repository;

import com.messageapp.api.modules.messaging.dto.ReactionCountDTO;
import com.messageapp.api.modules.messaging.model.Reaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reaction, UUID> {

	// ✅ FIX: this method MUST exist

	Optional<Reaction> findByMessageIdAndUserId(UUID messageId, UUID userId);

	@Query("""
			    SELECT new com.messageapp.api.modules.messaging.dto.ReactionCountDTO(
			        r.emoji, COUNT(r.id)
			    )
			    FROM Reaction r
			    WHERE r.messageId = :messageId
			    GROUP BY r.emoji
			""")
	List<ReactionCountDTO> countReactionsByEmoji(UUID messageId);
}
