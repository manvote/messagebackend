package com.messageapp.api.modules.user.dto;

public class UpdateProfileRequestDto {

    private String displayName;
    private String bio;
    private String profileImageUrl; // comes from Media module

    public String getDisplayName() { return displayName; }
    public String getBio() { return bio; }
    public String getProfileImageUrl() { return profileImageUrl; }
}
