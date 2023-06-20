package com.dmp.izza.controller;

import com.dmp.izza.service.AuthenticationService;
import com.dmp.izza.wrapper.auth.AuthenticationReq;
import com.dmp.izza.wrapper.auth.AuthenticationResp;
import com.dmp.izza.wrapper.auth.RegistrationReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(path = "/register",
            method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegistrationReq request) {
        AuthenticationResp response = authenticationService.register(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/login",
            method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationReq request) {
        AuthenticationResp response = authenticationService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
