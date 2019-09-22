package com.codetech.oauth2.services;

import com.codetech.oauth2.dto.AuthResponse;
import com.codetech.oauth2.dto.UserDTO;
import com.codetech.oauth2.exceptions.UsernameAlreadyExistsException;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by A Majutharan.
 * Date: 7/3/2019
 * Time: 5:01 PM
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Transactional
    public AuthResponse userInfo(Long userId) {
        User user = userRepository.getOne(userId);
        User minUserInfo = new User();
        minUserInfo.setId(user.getId());
        minUserInfo.setUsername(user.getUsername());
        AuthResponse authResponse = new AuthResponse();
//        authResponse.setUser(minUserInfo);
        authResponse.setId(user.getId());
        authResponse.setRole(user.getRoles());
        return authResponse;
    }

    public User saveUser (User newUser){
        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());

            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }
    }



}
