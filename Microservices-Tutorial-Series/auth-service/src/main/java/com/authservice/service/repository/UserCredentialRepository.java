package com.authservice.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authservice.service.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer>{

	

	Optional<UserCredential> findByName(String username);

}
