package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/4/2019
 * Time: 8:49 AM
 */
@Getter
@Setter
@Entity
public class BusinessProduct extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Business BusinessProfile Name is required")
    @Pattern(regexp = "^(?=.*[a-zA-Z]).{3,40}+$", message = "last name should be have at least one character, values between 3 to 40 and no spaces")
    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_profile")
    BusinessProfile businessProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    User user;
}
