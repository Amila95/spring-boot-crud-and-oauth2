package com.codetech.oauth2.security;

import com.codetech.oauth2.dto.AuthResponse;
import com.codetech.oauth2.exceptions.InvalidLoginResponse;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 10:16 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        if(username.isEmpty())throw new InvalidLoginResponse();


            User user = userRepository.findByUsername(username);
            System.out.println("password = " + user.getPassword());
        System.out.println("user = " + user);
            if (user ==null) {
                System.out.println("db username did not matched");
                throw new InvalidLoginResponse();
            }

            AuthResponse authResponse = userService.userInfo(user.getId());

            String role = authResponse.getRole();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            SecureUser secureUser = new SecureUser(username, user.getPassword(), authorities);
            secureUser.setId(user.getId());
            secureUser.setAuthResponse(authResponse);
            return secureUser;

    }

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username);
    }
}
