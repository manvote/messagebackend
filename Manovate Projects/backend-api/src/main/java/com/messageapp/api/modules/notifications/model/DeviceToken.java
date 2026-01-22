package com.messageapp.api.modules.notifications.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device_tokens")
public class DeviceToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @Column(nullable = false)
    private String token;

    private boolean active = true;

    public void setPhone(String phone) {
    }

    public void setToken(String token) {
    }

    public void setActive(boolean b) {
    }

    public String getToken() {
        return "";
    }

    // getters & setters
}
