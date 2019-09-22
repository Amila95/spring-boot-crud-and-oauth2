package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.Interest;
import com.codetech.oauth2.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:42 PM
 */
@Getter
@Setter
public class UserDTO {
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private  String roles;

    private boolean profileCompletion;

    private boolean userActive ;

    private boolean adminActive ;

    @JsonIgnore
    private String confirmPassword;

    private Set<Interest> interests = new HashSet<>();

    private UserProfile userProfile;
}