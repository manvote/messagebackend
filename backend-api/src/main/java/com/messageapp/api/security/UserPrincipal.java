package com.messageapp.api.security;


import com.messageapp.api.modules.user.model.user;
import lombok.AllArgsConstructor;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private final user user;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    @Override public String getUsername() { return user.getPhone(); }
    @Override public String getPassword() { return ""; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return user.isActive(); }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
