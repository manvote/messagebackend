package com.messageapp.api.modules.notifications.repository;

import com.messageapp.api.modules.notifications.model.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceTokenRepository
        extends JpaRepository<DeviceToken, Long> {

    List<DeviceToken> findByPhoneAndActiveTrue(String phone);
}
