package com.messageapp.api.modules.notifications.service;

import com.messageapp.api.modules.notifications.fcm.FcmClient;
import com.messageapp.api.modules.notifications.model.DeviceToken;
import com.messageapp.api.modules.notifications.repository.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final FcmClient fcmClient;

    @Override
    public void saveDeviceToken(String phone, String token) {

        DeviceToken deviceToken = new DeviceToken();
        deviceToken.setPhone(phone);
        deviceToken.setToken(token);
        deviceToken.setActive(true);

        deviceTokenRepository.save(deviceToken);
    }

    @Override
    public void notifyUser(String phone, String title, String body) {

        List<DeviceToken> tokens =
                deviceTokenRepository.findByPhoneAndActiveTrue(phone);

        for (DeviceToken token : tokens) {
            boolean success =
                    fcmClient.send(token.getToken(), title, body);

            if (!success) {
                token.setActive(false); // 🔴 failure handling
                deviceTokenRepository.save(token);
            }
        }
    }
}
