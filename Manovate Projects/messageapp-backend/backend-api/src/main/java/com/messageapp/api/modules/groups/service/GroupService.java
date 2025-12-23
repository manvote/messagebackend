package com.messageapp.api.modules.groups.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messageapp.api.modules.groups.model.CreateGroupRequest;
import com.messageapp.api.modules.groups.model.Group;
import com.messageapp.api.modules.groups.model.GroupMember;
import com.messageapp.api.modules.groups.model.GroupRole;
import com.messageapp.api.modules.groups.model.UpdateGroupRequest;
import com.messageapp.api.modules.groups.repository.GroupMemberRepository;
import com.messageapp.api.modules.groups.repository.GroupRepository;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMemberRepository memberRepository;

    /* CREATE GROUP */
    public Group createGroup(CreateGroupRequest request, UUID creatorId) {

        Group group = new Group();
        group.setName(request.getName());
        group.setIconUrl(request.getIconUrl());
        group.setCreatedBy(creatorId);

        Group savedGroup = groupRepository.save(group);

        // Creator = ADMIN
        GroupMember admin = new GroupMember();
        admin.setGroupId(savedGroup.getId());
        admin.setUserId(creatorId);
        admin.setRole(GroupRole.ADMIN);
        memberRepository.save(admin);

        // Add members
        for (UUID memberId : request.getMemberIds()) {
            if (!memberId.equals(creatorId)) {
                GroupMember member = new GroupMember();
                member.setGroupId(savedGroup.getId());
                member.setUserId(memberId);
                member.setRole(GroupRole.MEMBER);
                memberRepository.save(member);
            }
        }

        return savedGroup;
    }

    /* UPDATE GROUP */
    public Group updateGroup(UUID groupId, UpdateGroupRequest request, UUID userId) {

        checkAdmin(groupId, userId);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setName(request.getName());
        group.setIconUrl(request.getIconUrl());

        return groupRepository.save(group);
    }

    /* ADD MEMBER */
    public void addMember(UUID groupId, UUID adminId, UUID newUserId) {

        checkAdmin(groupId, adminId);

        GroupMember member = new GroupMember();
        member.setGroupId(groupId);
        member.setUserId(newUserId);
        member.setRole(GroupRole.MEMBER);

        memberRepository.save(member);
    }

    /* REMOVE MEMBER */
    public void removeMember(UUID groupId, UUID adminId, UUID userId) {

        checkAdmin(groupId, adminId);

        GroupMember member = memberRepository
                .findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        memberRepository.delete(member);
    }

    /* PERMISSION CHECK */
    private void checkAdmin(UUID groupId, UUID userId) {

        GroupMember member = memberRepository
                .findByGroupIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Not a group member"));

        if (member.getRole() != GroupRole.ADMIN) {
            throw new RuntimeException("Only admin allowed");
        }
    }
}
