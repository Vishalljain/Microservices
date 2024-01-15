package com.authservice.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authservice.service.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}
