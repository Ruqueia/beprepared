package com.rkm.beprepared.service;

import com.rkm.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.rkm.beprepared.dto.request.AuthenticationRequestForUser;
import com.rkm.beprepared.dto.response.TokenResponse;


public interface AuthenticationService {

    TokenResponse authenticate (AuthenticationRequestForUser authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest);

}
