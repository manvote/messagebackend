package com.messageapp.api.modules.groups.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageapp.api.modules.groups.dto.AddMemberRequest;
import com.messageapp.api.modules.groups.dto.CreateGroupRequest;
import com.messageapp.api.modules.groups.dto.UpdateGroupRequest;
import com.messageapp.api.modules.groups.model.GroupEntity;
import com.messageapp.api.modules.groups.service.GroupServiceLayer;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupServiceLayer groupService;

    @PostMapping
    public GroupEntity createGroup(
            @RequestBody CreateGroupRequest request,
            @RequestHeader("userId") UUID userId) {

        return groupService.createGroup(request, userId);
    }

    @PutMapping("/{groupId}")
    public GroupEntity updateGroup(
            @PathVariable UUID groupId,
            @RequestBody UpdateGroupRequest request,
            @RequestHeader("userId") UUID userId) {

        return groupService.updateGroup(groupId, request, userId);
    }

    @PostMapping("/{groupId}/members")
    public String addMember(
            @PathVariable UUID groupId,
            @RequestBody AddMemberRequest request,
            @RequestHeader("userId") UUID adminId) {

        groupService.addMember(groupId, adminId, request.getUserId());
        return "Member added";
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public String removeMember(
            @PathVariable UUID groupId,
            @PathVariable UUID userId,
            @RequestHeader("userId") UUID adminId) {

        groupService.removeMember(groupId, adminId, userId);
        return "Member removed";
    }
}
