package com.messageapp.api.modules.user.repository;


import com.messageapp.api.modules.user.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryClass extends JpaRepository<user, Long> {

    Optional<user> findByPhone(String phone);

    boolean existsByPhone(String phone);
}
