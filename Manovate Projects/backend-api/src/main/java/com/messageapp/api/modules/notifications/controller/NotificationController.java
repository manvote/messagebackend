package com.messageapp.api.modules.notifications.controller;

import com.messageapp.api.modules.notifications.dto.DeviceTokenRequestDto;
import com.messageapp.api.modules.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/register")
    public String registerToken(
            @AuthenticationPrincipal String phone,
            @RequestBody DeviceTokenRequestDto request) {

        notificationService.saveDeviceToken(phone, request.getToken());
        return "TOKEN_REGISTERED";
    }
}
