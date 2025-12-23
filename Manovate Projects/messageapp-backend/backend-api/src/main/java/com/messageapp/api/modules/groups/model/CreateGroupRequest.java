package com.messageapp.api.modules.groups.model;

import java.util.List;
import java.util.UUID;

public class CreateGroupRequest {
    private String name;
    private String iconUrl;
    private List<UUID> memberIds;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public List<UUID> getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(List<UUID> memberIds) {
		this.memberIds = memberIds;
	}
	@Override
	public String toString() {
		return "CreateGroupRequest [name=" + name + ", iconUrl=" + iconUrl + "]";
	}
    
}
