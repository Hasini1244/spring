package com.ijse.springintro.Service;

import org.springframework.stereotype.Service;

import com.ijse.springintro.Entity.User;



@Service
public interface UserService {
    User createUser(User user);
}