package org.bitirme.userservice.service;

import org.bitirme.userservice.request.AuthenticationRequest;
import org.bitirme.userservice.request.RegisterRequest;
import org.bitirme.userservice.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void validateToken(String token);
}
