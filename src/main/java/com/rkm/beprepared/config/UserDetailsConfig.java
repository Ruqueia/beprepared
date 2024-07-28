package com.rkm.beprepared.config;

import com.rkm.beprepared.repository.CitizenRepository;

import com.rkm.beprepared.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig implements UserDetailsService {

    private final UserRepository userRepository;
    private final CitizenRepository citizenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")) {
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
        }
        return citizenRepository.findCitizenByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("Citizen not Found!"));
    }
}
