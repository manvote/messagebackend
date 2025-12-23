package com.messageapp.api.modules.groups.model;
public class UpdateGroupRequest {
    private String name;
    private String iconUrl;
	@Override
	public String toString() {
		return "UpdateGroupRequest [name=" + name + ", iconUrl=" + iconUrl + "]";
	}
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
    
}
