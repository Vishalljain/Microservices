package com.user.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.service.entity.User;


public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(String userId);
}