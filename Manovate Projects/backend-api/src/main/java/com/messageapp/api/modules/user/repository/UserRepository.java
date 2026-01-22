package com.messageapp.api.modules.user.repository;

import com.messageapp.api.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
