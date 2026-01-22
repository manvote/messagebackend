package com.messageapp.api.modules.contacts.service;


import com.messageapp.api.modules.contacts.model.ContactEntity;
import com.messageapp.api.modules.contacts.model.ContactResponse;
import com.messageapp.api.modules.contacts.model.ContactSearchResponse;
import com.messageapp.api.modules.contacts.repository.ContactsRepository;
import com.messageapp.api.modules.contacts.service.ContactsService;
import com.messageapp.api.modules.user.repository.RepositoryClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;
    private final RepositoryClass userRepository;

    public ContactsServiceImpl(
            ContactsRepository contactsRepository,
            UserRepository userRepository
    ) {
        this.contactsRepository = contactsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addContact(Long ownerUserId, Long contactUserId) {

        if (ownerUserId.equals(contactUserId)) {
            throw new RuntimeException("Cannot add yourself");
        }

        boolean blockedEitherWay =
                !contactsRepository
                        .findRelationBetweenUsers(ownerUserId, contactUserId)
                        .isEmpty() &&
                        contactsRepository
                                .findRelationBetweenUsers(ownerUserId, contactUserId)
                                .stream()
                                .anyMatch(ContactEntity::isBlocked);

        if (blockedEitherWay) {
            throw new RuntimeException("Cannot add blocked user");
        }

        if (contactsRepository.existsByOwnerUserIdAndContactUserId(ownerUserId, contactUserId)) {
            throw new RuntimeException("Contact already exists");
        }

        ContactEntity contact = new ContactEntity();
        contact.setOwnerUserId(ownerUserId);
        contact.setContactUserId(contactUserId);

        contactsRepository.save(contact);
    }

    @Override
    public void removeContact(Long ownerUserId, Long contactUserId) {
        ContactEntity contact = contactsRepository
                .findByOwnerUserIdAndContactUserId(ownerUserId, contactUserId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactsRepository.delete(contact);
    }

    @Override
    public List<ContactResponse> getContacts(Long ownerUserId) {
        return contactsRepository.findByOwnerUserId(ownerUserId)
                .stream()
                .map(c -> new ContactResponse(
                        c.getContactUserId(),
                        c.isBlocked()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void blockContact(Long ownerUserId, Long contactUserId) {
        ContactEntity contact = contactsRepository
                .findByOwnerUserIdAndContactUserId(ownerUserId, contactUserId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (contact.isBlocked()) {
            throw new RuntimeException("User already blocked");
        }

        contact.setBlocked(true);
        contactsRepository.save(contact);
    }

    @Override
    public void unblockContact(Long ownerUserId, Long contactUserId) {
        ContactEntity contact = contactsRepository
                .findByOwnerUserIdAndContactUserId(ownerUserId, contactUserId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.isBlocked()) {
            throw new RuntimeException("User is not blocked");
        }

        contact.setBlocked(false);
        contactsRepository.save(contact);
    }
    @Override
    public List<ContactSearchResponse> searchContacts(Long ownerUserId, String keyword) {

        String key = keyword.toLowerCase();

        return contactsRepository.findByOwnerUserId(ownerUserId)
                .stream()
                .filter(c -> !c.isBlocked()) // owner blocked contact
                .filter(c ->
                        !contactsRepository
                                .existsByOwnerUserIdAndContactUserIdAndBlockedTrue(
                                        c.getContactUserId(),
                                        ownerUserId
                                )
                )
                .map(c -> userRepository.findById(c.getContactUserId()).orElse(null))
                .filter(u -> u != null &&
                        (u.getName().toLowerCase().contains(key)
                                || u.getPhone().contains(key)))
                .map(u -> new ContactSearchResponse(
                        u.getId(),
                        u.getName(),
                        u.getPhone()
                ))
                .toList();
    }



}
