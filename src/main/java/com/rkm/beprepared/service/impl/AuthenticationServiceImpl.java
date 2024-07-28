package com.rkm.beprepared.service.impl;

import com.rkm.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.rkm.beprepared.dto.request.AuthenticationRequestForUser;
import com.rkm.beprepared.dto.response.TokenResponse;
import com.rkm.beprepared.model.Citizen;
import com.rkm.beprepared.model.Token;
import com.rkm.beprepared.model.User;
import com.rkm.beprepared.repository.CitizenRepository;
import com.rkm.beprepared.repository.TokenRepository;
import com.rkm.beprepared.repository.UserRepository;
import com.rkm.beprepared.security.JWTService;
import com.rkm.beprepared.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

   private final JWTService jwtService;
   private final UserRepository userRepository;
   private final TokenRepository tokenRepository;
   private final CitizenRepository citizenRepository;
   private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       authenticationRequest.getPhone(),
                       authenticationRequest.getOtp()
               )
       );
       var citizen = citizenRepository.findCitizenByPhone(authenticationRequest.getPhone()).orElseThrow();
       var token = jwtService.generateToken(citizen);
       citizen.setOtp(null);
       saveCitizenToken(citizen, token);
       citizenRepository.save(citizen);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    private void saveUserToken(User user, String token){
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

    private void saveCitizenToken(Citizen citizen, String token){
        var jwtToken = Token.builder()
                .citizen(citizen)
                .token(token)
                .expired(false)
                .revoked(false)
                .createAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);

    }
}
