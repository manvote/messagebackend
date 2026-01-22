package com.messageapp.api.modules.user.controller;

import com.messageapp.api.modules.user.dto.UpdateProfileRequestDto;
import com.messageapp.api.modules.user.dto.UserProfileResponseDto;
import com.messageapp.api.modules.user.model.User;
import com.messageapp.api.modules.user.repository.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final BlockService.UserService userService;

    // ✅ EXISTING – DO NOT CHANGE
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal String phone) {
        return userService.getByPhone(phone);
    }

    // ✅ NEW – PROFILE RESPONSE DTO
    @GetMapping("/profile")
    public UserProfileResponseDto getProfileDetails(
            @AuthenticationPrincipal String phone) {

        return userService.getProfile(phone);
    }

    // ✅ NEW – UPDATE PROFILE
    @PutMapping("/profile")
    public UserProfileResponseDto updateProfile(
            @AuthenticationPrincipal String phone,
            @RequestBody UpdateProfileRequestDto request) {

        return userService.updateProfile(phone, request);
    }

    // ✅ EXISTING – DO NOT CHANGE
    @PostMapping("/deactivate")
    public String deactivate(@AuthenticationPrincipal String phone) {
        userService.deactivateUser(phone);
        return "USER_DEACTIVATED";
    }
}
