package com.messageapp.api.modules.user.service;


import com.messageapp.api.common.exceptions.ApiException;
import com.messageapp.api.modules.user.model.user;
import com.messageapp.api.modules.user.repository.BlockService;
import com.messageapp.api.modules.user.repository.RepositoryClass;
import com.messageapp.api.security.RoleEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BlockService.UserService {

    private final RepositoryClass userRepository;

    @Override
    public user getByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .orElseThrow();
    }

    @Override
    public user createUser(String phone) throws Throwable {

        if (userRepository.existsByPhone(phone)) {
            throw new Throwable("User already exists");
        }

        return userRepository.save(
                user.builder()
                        .phone(phone)
                        .role(RoleEnum.USER)
                        .active(true)
                        .build()
        );
    }

    @Override
    public void deactivateUser(String phone) {
        user u = getByPhone(phone);
        u.setActive(false);
        userRepository.save(u);
    }
}

