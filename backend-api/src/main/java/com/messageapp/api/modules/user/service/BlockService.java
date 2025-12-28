package com.messageapp.api.modules.user.service;


import com.messageapp.api.modules.user.model.BlockedUser;
import com.messageapp.api.modules.user.repository.BlockService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final EntityManager entityManager;

    @Override
    public boolean isBlocked(String phone) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(b) FROM BlockedUser b WHERE b.phone = :phone",
                        Long.class
                ).setParameter("phone", phone)
                .getSingleResult();

        return count > 0;
    }

    @Override
    public void blockUser(String phone) {
        entityManager.persist(new BlockedUser(null, phone));
    }

    @Override
    public void unblockUser(String phone) {
        entityManager.createQuery(
                "DELETE FROM BlockedUser b WHERE b.phone = :phone"
        ).setParameter("phone", phone).executeUpdate();
    }
}
