package com.messageapp.api.modules.groups.dto;

import java.util.UUID;

import lombok.Data;
@Data
public class AddMemberRequest {
    private UUID userId;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
    
    
}
