package com.messageapp.api.modules.groups.model;

import java.util.UUID;

public class AddMemberRequest {
    private UUID userId;

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddMemberRequest []";
	}
    
}
