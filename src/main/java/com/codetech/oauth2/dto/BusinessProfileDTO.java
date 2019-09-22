package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.BusinessCategory;
import com.codetech.oauth2.model.BusinessRegistionType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:37 PM
 */

@Getter
@Setter
public class BusinessProfileDTO {

    private Long id;

    @NotBlank(message = "Business Registration Number is required")
    private String businessRegistrationNumber;

    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9_ ].{3,40}+$", message = "name should be have at least one character, values between 3 to 40 and no spaces")
    private String name;

    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please provide a valid email address")
    private String emailId;

    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNumber01;

    @Pattern(regexp = "^$|^(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNumber02;

    private String logo;

    private Boolean status;

    private Boolean deleted;
    @NotBlank(message = "Role is required")
    private String role;

    private String description;

    private Set<BusinessCategory> businessCategorySet = new HashSet<>();

    @NotBlank(message = "Business Registration  is required")
    private String businessRegistionTypes;
}
