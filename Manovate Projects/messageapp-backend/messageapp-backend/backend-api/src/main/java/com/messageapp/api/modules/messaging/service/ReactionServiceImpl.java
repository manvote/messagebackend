package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.dto.ReactionCountDTO;
import com.messageapp.api.modules.messaging.dto.ReactionEvent;
import com.messageapp.api.modules.messaging.model.Reaction;
import com.messageapp.api.modules.messaging.repository.ReactionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;

    public ReactionServiceImpl(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    @Transactional
    public Reaction saveOrUpdateReaction(ReactionEvent event) {

        return reactionRepository
                .findByMessageIdAndUserId(event.getMessageId(), event.getUserId())
                .map(existing -> {
                    existing.setEmoji(event.getEmoji());
                    existing.setReactedAt(Instant.now());
                    return reactionRepository.save(existing);
                })
                .orElseGet(() -> {
                    Reaction reaction = new Reaction();
                    reaction.setId(UUID.randomUUID());
                    reaction.setMessageId(event.getMessageId());
                    reaction.setUserId(event.getUserId());
                    reaction.setEmoji(event.getEmoji());
                    reaction.setReactedAt(Instant.now());
                    return reactionRepository.save(reaction);
                });
    }

    @Override
    public List<ReactionCountDTO> getReactionCounts(UUID messageId) {
        return reactionRepository.countReactionsByEmoji(messageId);
    }
}
