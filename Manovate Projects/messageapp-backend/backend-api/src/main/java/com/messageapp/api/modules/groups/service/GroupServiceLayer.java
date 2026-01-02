	package com.messageapp.api.modules.groups.service;

	import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messageapp.api.modules.groups.dto.CreateGroupRequest;
import com.messageapp.api.modules.groups.dto.UpdateGroupRequest;
import com.messageapp.api.modules.groups.model.GroupEntity;
import com.messageapp.api.modules.groups.model.GroupMemberEntity;
import com.messageapp.api.modules.groups.model.GroupRoles;
import com.messageapp.api.modules.groups.repository.GroupMemberRepository;
import com.messageapp.api.modules.groups.repository.GroupRepo;

	@Service

public class GroupServiceLayer {


	    @Autowired
	    private GroupRepo groupRepository;

	    @Autowired
	    private GroupMemberRepository memberRepository;

	    /* CREATE GROUP */
	    public GroupEntity createGroup(CreateGroupRequest request, UUID creatorId) {

	        GroupEntity group = new GroupEntity();
	        group.setName(request.getName());
	        group.setIconUrl(request.getIconUrl());
	        group.setCreatedBy(creatorId);

	        GroupEntity savedGroup = groupRepository.save(group);

	        // Creator = ADMIN
	        GroupMemberEntity admin = new GroupMemberEntity();
	        admin.setGroupId(savedGroup.getId());
	        admin.setUserId(creatorId);
	        admin.setRole(GroupRoles.ADMIN);
	        memberRepository.save(admin);

	        // Add members
	        for (UUID memberId : request.getMemberIds()) {
	            if (!memberId.equals(creatorId)) {
	                GroupMemberEntity member = new GroupMemberEntity();
	                member.setGroupId(savedGroup.getId());
	                member.setUserId(memberId);
	                member.setRole(GroupRoles.MEMBER);
	                memberRepository.save(member);
	            }
	        }

	        return savedGroup;
	    }

	    /* UPDATE GROUP */
	    public GroupEntity updateGroup(UUID groupId, UpdateGroupRequest request, UUID userId) {

	        checkAdmin(groupId, userId);

	        GroupEntity group = groupRepository.findById(groupId)
	                .orElseThrow(() -> new RuntimeException("Group not found"));

	        group.setName(request.getName());
	        group.setIconUrl(request.getIconUrl());

	        return groupRepository.save(group);
	    }

	    /* ADD MEMBER */
	    public void addMember(UUID groupId, UUID adminId, UUID newUserId) {

	        checkAdmin(groupId, adminId);

	        GroupMemberEntity member = new GroupMemberEntity();
	        member.setGroupId(groupId);
	        member.setUserId(newUserId);
	        member.setRole(GroupRoles.MEMBER);

	        memberRepository.save(member);
	    }

	    /* REMOVE MEMBER */
	    public void removeMember(UUID groupId, UUID adminId, UUID userId) {

	        checkAdmin(groupId, adminId);

	        GroupMemberEntity member = memberRepository
	                .findByGroupIdAndUserId(groupId, userId)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        memberRepository.delete(member);
	    }

	    /* PERMISSION CHECK */
	    private void checkAdmin(UUID groupId, UUID userId) {

	        GroupMemberEntity member = memberRepository
	                .findByGroupIdAndUserId(groupId, userId)
	                .orElseThrow(() -> new RuntimeException("Not a group member"));

	        if (member.getRole() != GroupRoles.ADMIN) {
	            throw new RuntimeException("Only admin allowed");
	        }
	    }
	}


