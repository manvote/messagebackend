package com.messageapp.api.modules.messaging.service;

import com.messageapp.api.modules.messaging.dto.ReactionCountDTO;
import com.messageapp.api.modules.messaging.dto.ReactionEvent;
import com.messageapp.api.modules.messaging.model.Reaction;

import java.util.List;
import java.util.UUID;

public interface ReactionService {

    Reaction saveOrUpdateReaction(ReactionEvent event);

    List<ReactionCountDTO> getReactionCounts(UUID messageId);
}
