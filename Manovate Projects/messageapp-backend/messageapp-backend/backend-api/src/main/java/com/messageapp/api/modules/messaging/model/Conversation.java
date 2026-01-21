package com.messageapp.api.modules.messaging.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "conversations")
public class Conversation {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private String type; // ONE_TO_ONE

	@CreationTimestamp
	private LocalDateTime createdAt;
}
