package com.messageapp.api.modules.groups.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messageapp.api.modules.groups.model.GroupMemberEntity;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, UUID> {

    Optional<GroupMemberEntity> findByGroupIdAndUserId(UUID groupId, UUID userId);

    List<GroupMemberEntity> findByGroupId(UUID groupId);
}
