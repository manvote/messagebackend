package com.messageapp.api.modules.contacts.service;


import com.messageapp.api.modules.contacts.model.ContactResponse;

import java.util.List;

public interface ContactsService {

        void addContact(Long ownerUserId, Long contactUserId);

        void removeContact(Long ownerUserId, Long contactUserId);

        List<ContactResponse> getContacts(Long ownerUserId);

        void blockContact(Long ownerUserId, Long contactUserId);

        void unblockContact(Long ownerUserId, Long contactUserId);

        List<ContactResponse> searchContacts(Long ownerUserId, String keyword);

}





