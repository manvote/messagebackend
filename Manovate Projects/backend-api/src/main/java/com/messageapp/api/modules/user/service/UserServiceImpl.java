package com.messageapp.api.modules.user.service;

import com.messageapp.api.common.exceptions.ApiException;
import com.messageapp.api.modules.user.dto.UpdateProfileRequestDto;
import com.messageapp.api.modules.user.dto.UserProfileResponseDto;
import com.messageapp.api.modules.user.model.User;
import com.messageapp.api.modules.user.repository.BlockService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BlockService.UserService {

    private final EntityManager entityManager;

    // -------------------------
    // EXISTING METHODS
    // -------------------------

    @Override
    public User getByPhone(String phone) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.phone = :phone",
                        User.class
                ).setParameter("phone", phone)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new ApiException("USER_NOT_FOUND"));
    }

    @Override
    public User createUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deactivateUser(String phone) {
        entityManager.createQuery(
                "UPDATE User u SET u.active = false WHERE u.phone = :phone"
        ).setParameter("phone", phone).executeUpdate();
    }

    // -------------------------
    // 🔽 NEW PROFILE METHODS (VISHNU TASK)
    // -------------------------

    @Override
    public UserProfileResponseDto getProfile(String phone) {

        User user = getByPhone(phone);

        return mapToProfileDto(user);
    }

    @Override
    public UserProfileResponseDto updateProfile(
            String phone,
            UpdateProfileRequestDto request) {

        User user = getByPhone(phone);

        if (request.getDisplayName() != null)
            user.setDisplayName(request.getDisplayName());

        if (request.getBio() != null)
            user.setBio(request.getBio());

        if (request.getProfileImageUrl() != null)
            user.setProfileImageUrl(request.getProfileImageUrl());

        entityManager.merge(user);
        return mapToProfileDto(user);
    }

    // -------------------------
    // MAPPER
    // -------------------------

    private UserProfileResponseDto mapToProfileDto(User user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getDisplayName(),
                user.getPhone(),
                user.getBio(),
                user.getProfileImageUrl()
        );
    }
}
