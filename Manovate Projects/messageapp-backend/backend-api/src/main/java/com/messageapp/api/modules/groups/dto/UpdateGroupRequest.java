package com.messageapp.api.modules.groups.dto;

import lombok.Data;

@Data
public class UpdateGroupRequest {
    private String name;
    private String iconUrl;
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
	@Override
	public String toString() {
		return "UpdateGroupRequest [name=" + name + ", iconUrl=" + iconUrl + "]";
	}
    
}
