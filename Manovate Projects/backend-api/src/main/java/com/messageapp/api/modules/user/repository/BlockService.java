package com.messageapp.api.modules.user.repository;

import com.messageapp.api.modules.user.dto.UpdateProfileRequestDto;
import com.messageapp.api.modules.user.dto.UserProfileResponseDto;
import com.messageapp.api.modules.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockService {

    boolean isBlocked(String phone);

    void blockUser(String phone);

    void unblockUser(String phone);

    interface UserService {

        // ✅ EXISTING (DO NOT CHANGE)
        User getByPhone(String phone);

        User createUser(String phone) throws Throwable;

        void deactivateUser(String phone);

        // 🔽 NEW – PROFILE APIs (Vishnu’s task)

        /**
         * Fetch profile details for logged-in user
         */
        UserProfileResponseDto getProfile(String phone);

        /**
         * Update profile details (name, bio, image)
         */
        UserProfileResponseDto updateProfile(
                String phone,
                UpdateProfileRequestDto request
        );
    }
}
