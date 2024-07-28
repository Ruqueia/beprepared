package com.rkm.beprepared.service.impl;

import com.rkm.beprepared.dto.response.StatsResponse;
import com.rkm.beprepared.exception.BadRequestException;
import com.rkm.beprepared.exception.EntityNotFoundException;
import com.rkm.beprepared.model.User;
import com.rkm.beprepared.model.enums.Role;
import com.rkm.beprepared.repository.AlertRepository;
import com.rkm.beprepared.repository.CitizenRepository;
import com.rkm.beprepared.repository.UserRepository;
import com.rkm.beprepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository  citizenRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public String createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new BadRequestException("Email already exists");
        }
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuario criado com sucesso";
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
