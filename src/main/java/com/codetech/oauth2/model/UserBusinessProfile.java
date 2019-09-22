package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:09 PM
 */

@Getter
@Setter
@Entity
public class UserBusinessProfile  extends  Auditable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "business_profile")
    BusinessProfile businessProfile;

    @NotBlank(message = "role is required")
    String role;


    private boolean active = true;
}
