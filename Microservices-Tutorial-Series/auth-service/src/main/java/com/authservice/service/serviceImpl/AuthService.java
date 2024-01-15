package com.authservice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.service.entity.UserCredential;
import com.authservice.service.repository.UserCredentialRepository;

@Service
public class AuthService {
	
	@Autowired
	UserCredentialRepository userCredentialRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private JwtService jwtService;
	//we will write a method to save a user in db once user is there in my db then only i can authenticate i can generate the token using the user details.
	public String saveUser(UserCredential userCredential) {
		//userCredentialRepository.save(userCredential);//we are storing pw as a string so we will define pw encoder
		//now create a auth config class create a bean password encoder 
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		userCredentialRepository.save(userCredential);
		return "User added to the db successfully";
	}
	
	
	

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
//now we need to write a method to register the user then a method to get a jwt token validate that token.To generate and validate we will write a JWTSERVICE class 