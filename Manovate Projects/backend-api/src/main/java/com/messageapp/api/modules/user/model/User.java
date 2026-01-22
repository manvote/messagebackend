package com.messageapp.api.modules.user.model;


import com.messageapp.api.security.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private boolean active;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

}

