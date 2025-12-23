package com.messageapp.api.modules.contacts.controller;

import com.messageapp.api.modules.contacts.model.ContactRequest;
import com.messageapp.api.modules.contacts.model.ContactResponse;
import com.messageapp.api.modules.contacts.service.ContactsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/contacts")
    public class ContactsController {

        private final ContactsService contactsService;

        public ContactsController(ContactsService contactsService) {
            this.contactsService = contactsService;
        }

        @PostMapping
        public void addContact(
                @RequestHeader("X-USER-ID") Long ownerUserId,
                @RequestBody ContactRequest request
        ) {
            contactsService.addContact(ownerUserId, request.getContactUserId());
        }
        @GetMapping("/search")
        public List<ContactResponse> searchContacts(
                @RequestHeader("X-USER-ID") Long ownerUserId,
                @RequestParam String keyword
        ) {
            return contactsService.searchContacts(ownerUserId, keyword);
        }



        @DeleteMapping("/{contactUserId}")
        public void removeContact(
                @RequestHeader("X-USER-ID") Long ownerUserId,
                @PathVariable Long contactUserId
        ) {
            contactsService.removeContact(ownerUserId, contactUserId);
        }


        @GetMapping
        public List<ContactResponse> getContacts(
                @RequestHeader("X-USER-ID") Long ownerUserId
        ) {
            return contactsService.getContacts(ownerUserId);
        }


        @PostMapping("/{contactUserId}/block")
        public void blockContact(
                @RequestHeader("X-USER-ID") Long ownerUserId,
                @PathVariable Long contactUserId
        ) {
            contactsService.blockContact(ownerUserId, contactUserId);
        }


        @PostMapping("/{contactUserId}/unblock")
        public void unblockContact(
                @RequestHeader("X-USER-ID") Long ownerUserId,
                @PathVariable Long contactUserId
        ) {
            contactsService.unblockContact(ownerUserId, contactUserId);
        }
    }

