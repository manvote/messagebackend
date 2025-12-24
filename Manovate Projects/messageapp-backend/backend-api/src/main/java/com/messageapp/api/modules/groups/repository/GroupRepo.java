package com.messageapp.api.modules.groups.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messageapp.api.modules.groups.model.GroupEntity;

public interface GroupRepo extends JpaRepository<GroupEntity, UUID> {
}
