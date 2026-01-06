package com.messageapp.api.modules.contacts.repository;

import com.messageapp.api.modules.contacts.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    boolean existsByOwnerUserIdAndContactUserIdAndBlockedTrue(
            Long ownerUserId,
            Long contactUserId
    );

    @Query("""
    SELECT c FROM ContactEntity c
    WHERE 
    (c.ownerUserId = :user1 AND c.contactUserId = :user2)
    OR
    (c.ownerUserId = :user2 AND c.contactUserId = :user1)
    """)
    List<ContactEntity> findRelationBetweenUsers(
            Long user1,
            Long user2
    );


}

