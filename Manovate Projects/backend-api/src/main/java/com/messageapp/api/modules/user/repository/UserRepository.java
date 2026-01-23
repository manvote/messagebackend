package com.messageapp.api.modules.user.repository;

import com.messageapp.api.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 FIND USER BY PHONE NUMBER
    Optional<User> findByPhone(String phone);
}
