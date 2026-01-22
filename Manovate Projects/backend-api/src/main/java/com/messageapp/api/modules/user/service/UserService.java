package com.messageapp.api.modules.user.service;

import com.messageapp.api.modules.user.dto.UpdateProfileRequestDto;
import com.messageapp.api.modules.user.dto.UserProfileResponseDto;

public interface UserService {

    UserProfileResponseDto getMyProfile(Long userId);

    UserProfileResponseDto updateProfile(Long userId,
                                         UpdateProfileRequestDto request);
}
