package com.messageapp.api.modules.contacts.repository;

import com.messageapp.api.modules.contacts.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository extends JpaRepository<ContactEntity, Long> {

        List<ContactEntity> findByOwnerUserId(Long ownerUserId);

        Optional<ContactEntity> findByOwnerUserIdAndContactUserId(
                Long ownerUserId,
                Long contactUserId
        );

        boolean existsByOwnerUserIdAndContactUserId(
                Long ownerUserId,
                Long contactUserId
        );

    }

