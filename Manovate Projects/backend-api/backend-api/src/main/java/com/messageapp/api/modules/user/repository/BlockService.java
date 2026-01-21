package com.messageapp.api.modules.user.repository;

import com.messageapp.api.modules.user.model.user;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockService {

    boolean isBlocked(String phone);

    void blockUser(String phone);

    void unblockUser(String phone);

    interface UserService {

        user getByPhone(String phone);

        user createUser(String phone) throws Throwable;

        void deactivateUser(String phone);
    }
}

