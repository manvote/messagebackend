package com.messageapp.api.modules.notifications.service;

public interface NotificationService {

    void saveDeviceToken(String phone, String token);

    void notifyUser(String phone, String title, String body);
}
