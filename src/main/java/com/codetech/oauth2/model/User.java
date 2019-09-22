package com.codetech.oauth2.model;

import com.codetech.oauth2.validator.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.*;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 12:19 PM
 */
@Getter
@Setter
@Entity
public class User implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please provide a valid email address")
    @Column(unique = true)
    private String username;

    @ValidPassword
    private String password;

    private  String roles = "User";

    @Transient
    private String confirmPassword;

    private boolean profileCompletion = false;

    private boolean userActive = true;

    private boolean adminActive = true;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private UserProfile userProfile;



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    Set<UserBusinessProfile> userBusinessProfiles;

    @OneToMany(fetch = FetchType.LAZY   ,mappedBy = "user")
    @JsonIgnore
    Set<BusinessProduct> businessProducts;

    @OneToMany(fetch = FetchType.LAZY   ,mappedBy = "user")
    @JsonIgnore
    Set<BusinessService> businessServices;

    @ManyToMany()
    @JoinTable(name = "user_interest",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id")})
            private Set<Interest> interests = new HashSet<>();



}
