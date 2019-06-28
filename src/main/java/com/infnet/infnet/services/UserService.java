package com.infnet.infnet.services;

import com.infnet.infnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
