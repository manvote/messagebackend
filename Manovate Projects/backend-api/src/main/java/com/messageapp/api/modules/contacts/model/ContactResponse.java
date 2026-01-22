package com.messageapp.api.modules.contacts.model;

public class ContactResponse {

    private final Long contactUserId;
    private final boolean blocked;

    public ContactResponse(Long contactUserId, boolean blocked) {
        this.contactUserId = contactUserId;
        this.blocked = blocked;
    }

    public Long getContactUserId() {
        return contactUserId;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
