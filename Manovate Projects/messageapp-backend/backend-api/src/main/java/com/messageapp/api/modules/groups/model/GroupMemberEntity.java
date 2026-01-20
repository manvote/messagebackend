package com.messageapp.api.modules.groups.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_members")
public class GroupMemberEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID groupId;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private GroupRoles role;

    @CreationTimestamp
    private LocalDateTime joinedAt;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getGroupId() {
		return groupId;
	}

	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public GroupRoles getRole() {
		return role;
	}

	public void setRole(GroupRoles role) {
		this.role = role;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	@Override
	public String toString() {
		return "GroupMemberEntity [id=" + id + ", groupId=" + groupId + ", userId=" + userId + ", role=" + role
				+ ", joinedAt=" + joinedAt + "]";
	}
    
}
