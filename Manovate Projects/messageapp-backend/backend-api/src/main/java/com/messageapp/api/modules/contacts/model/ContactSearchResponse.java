package com.messageapp.api.modules.contacts.model;

public class ContactSearchResponse {

    private Long userId;
    private String name;
    private String phone;

    public ContactSearchResponse(Long userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
