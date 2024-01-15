package com.authservice.service.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authservice.service.entity.UserCredential;
import com.authservice.service.repository.UserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
    //AS ABOVE METHOD RETURN USERDETAILS WE NEED TO CONVERT Optional<UserCredential> credential = repository.findByName(username);
    //THE CREDENTIAL WHICH WE GOT IT IS A TYPE OF UserCredential BUT WE WANT UserDetails SO YOU NEED TO CONVERT AND SEND IT BACK
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = repository.findByName(username);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}



//AT LAST WE HAVE CREATED OUR USER DETAIL SERVICE SO THAT AUTHENTICATION MANAGER CAN DIRECTLY TALK TO THE USER DETAIL SERVICE FOR THAT U NEED THE HELP FROM AUTHENTICATION PROVIDER SO SIMPLY CREATE A BEAN OF AUTHENTICATION PROVIDER 