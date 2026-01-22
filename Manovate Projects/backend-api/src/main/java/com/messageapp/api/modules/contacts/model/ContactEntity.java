package com.messageapp.api.modules.contacts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "contacts",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"owner_user_id", "contact_user_id"}
    )
)
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_user_id", nullable = false)
    private Long ownerUserId;

    @Column(name = "contact_user_id", nullable = false)
    private Long contactUserId;

    @Column(name = "is_blocked")
    private boolean blocked = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    public Long getId() {
        return id;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Long getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(Long contactUserId) {
        this.contactUserId = contactUserId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
