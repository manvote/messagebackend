package com.messageapp.api.modules.user.dto;

public class UserProfileResponseDto {

    private Long id;
    private String displayName;
    private String phone;
    private String bio;
    private String profileImageUrl;

    public UserProfileResponseDto(Long id, String displayName,
                                  String phone, String bio,
                                  String profileImageUrl) {
        this.id = id;
        this.displayName = displayName;
        this.phone = phone;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getPhone() { return phone; }
    public String getBio() { return bio; }
    public String getProfileImageUrl() { return profileImageUrl; }
}
