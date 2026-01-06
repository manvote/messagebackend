package com.messageapp.api.security;


import com.messageapp.api.modules.user.repository.RepositoryClass;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final RepositoryClass userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone)
            throws UsernameNotFoundException {

        return userRepository.findByPhone(phone)
                .map(UserPrincipal::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}
