package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:08 PM
 */

@Getter
@Setter
@Entity
public class BusinessProfile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Business Registration Number is required")
    // should include brn regex validation
    @Column(name = "business_registration_number", unique = true)
    private String businessRegistrationNumber;

    @NotBlank(message = "Business BusinessProfile Name is required")
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9_ ].{3,40}+$", message = "name should be have at least one character, values between 3 to 40 and no spaces")
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = "Email id is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please provide a valid email address")
    @Column(name = "email_id", unique = true)
    private String emailId;

    @NotEmpty(message = "Contact is required")
    @Column(name = "contact_number_01")
    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNumber01;

    @Column(name = "contact_number_02")
//    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNumber02;

    @Column(name = "logo")
    private String logo;

    private String description;


    @Value("false")
    @Column(name = "status")
    private boolean status;

    @Value("false")
    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "businessProfile")
    @JsonIgnore
    Set<UserBusinessProfile> userBusinessProfiles;



//    @OneToMany(mappedBy = "businessProfile")
//    @JsonIgnore
//    Set<BusinessRegistionType> businessRegistionTypes;

    @ManyToOne
    @JoinColumn(name = "businessRegistionType")
    BusinessRegistionType businessRegistionTypes;

    //created by Supun Dilshan
    @OneToMany (mappedBy = "businessProfiles")
    @JsonIgnore
    Set <Branch> branches;



//    @ManyToMany(fetch = FetchType.LAZY, mappedBy="businessProfileSet")
//    @Getter(onMethod = @__( @JsonIgnore))
//    @Setter
//    private Set<BusinessCategory> businessCategorySet = new HashSet<BusinessCategory>();


    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "businessProfile_businessCategory",
            joinColumns = {@JoinColumn(name = "businessProfile_id")},
            inverseJoinColumns = {@JoinColumn(name = "businessCategory_id")})
    private Set<BusinessCategory> businessCategorySet = new HashSet<BusinessCategory>();


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "businessProfile")
    private Address address;


    @OneToMany(fetch = FetchType.LAZY   ,mappedBy = "businessProfile")
    @JsonIgnore
    Set<BusinessProduct> businessProducts;

    @OneToMany(fetch = FetchType.LAZY   ,mappedBy = "businessProfile")
    @JsonIgnore
    Set<BusinessService> businessServices;
}

