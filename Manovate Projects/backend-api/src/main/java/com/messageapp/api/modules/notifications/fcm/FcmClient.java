package com.messageapp.api.modules.notifications.fcm;

import org.springframework.stereotype.Component;

@Component
public class FcmClient {

    public boolean send(String token, String title, String body) {
        // 🔹 Placeholder for Firebase Admin SDK
        // Later replace with real FCM send logic

        System.out.println("Sending FCM → Token: " + token);
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);

        return true; // assume success for now
    }
}
