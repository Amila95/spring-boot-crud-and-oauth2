package com.codetech.oauth2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:43 PM
 */
@Getter
@Setter
public class UserProfileDTO {
    private Long id;

//    @NotBlank(message = "FirstName cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "first name should be have at least one character, values between 3 to 40 and no spaces")
    private String firstName;


    @Pattern(regexp = "^$|^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "middle name should be have at least one character, values between 3 to 40 and no spaces")
    private String middleName;

//    @NotBlank(message = "LastName cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "last name should be have at least one character, values between 3 to 40 and no spaces")
    private String lastName;

    @NotBlank(message = "contact number cannot be blank")
    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNo;

    @Pattern(regexp = "(Male|Female|Other)", message = "Gender must must a Male,Female or Other ")
    private String gender;
}
