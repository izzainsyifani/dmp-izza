package com.dmp.izza.service;

import com.dmp.izza.config.JwtService;
import com.dmp.izza.model.Role;
import com.dmp.izza.model.User;
import com.dmp.izza.repository.UserRepository;
import com.dmp.izza.wrapper.auth.AuthenticationReq;
import com.dmp.izza.wrapper.auth.AuthenticationResp;
import com.dmp.izza.wrapper.auth.RegistrationReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResp register(RegistrationReq request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResp.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResp login(AuthenticationReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResp.builder()
                .token(jwtToken)
                .build();
    }
}
