package com.messageapp.api.modules.user.controller;

import com.messageapp.api.modules.user.model.user;
import com.messageapp.api.modules.user.repository.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final BlockService.UserService userService;

    @GetMapping("/me")
    public user getProfile(@AuthenticationPrincipal String phone) {
        return userService.getByPhone(phone);
    }

    @PostMapping("/deactivate")
    public String deactivate(@AuthenticationPrincipal String phone) {
        userService.deactivateUser(phone);
        return "USER_DEACTIVATED";
    }
}

