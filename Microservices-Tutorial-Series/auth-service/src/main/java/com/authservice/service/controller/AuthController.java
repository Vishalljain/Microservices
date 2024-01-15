package com.authservice.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.authservice.service.dto.AuthRequest;
import com.authservice.service.entity.UserCredential;
import com.authservice.service.exception.ForbiddenRuntimeException;
import com.authservice.service.serviceImpl.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

	private boolean authenticated;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));//this line will authenticate if user is find in db then pls authenticate but authentication manager need to talk with db so we use userDetailService CREATE A BEAN OF USER DETAIL SERVICE 
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
        	System.out.println("---------------");
           throw new ForbiddenRuntimeException("invalid access for username :"+authRequest.getUsername());
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
//    //above code we have extra line because imagine i have 2 data vishal and ravi as un in db but i dont have john now john access this method n generate a token but its details is not there in db then how can he generate token first he needs to be authenticated
//    @PostMapping("/token")
//    public String getToken(@RequestBody AuthRequest authRequest) {
//            return service.generateToken(authRequest.getUsername());
//    }
//    
    
}