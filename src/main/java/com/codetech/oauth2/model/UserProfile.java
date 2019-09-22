package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:04 PM
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "FirstName cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "first name should be have at least one character, values between 3 to 40 and no spaces")
    private String firstName;

//    @Pattern(regexp = "^(?=.*[a-zA-Z].{0,40})(?=\\S+$).{0,40}+$", message = "middle name should be have at least one character, values between 3 to 40 and no spaces")
    private String middleName;

    @NotBlank(message = "LastName cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=\\S+$).{3,40}+$", message = "last name should be have at least one character, values between 3 to 40 and no spaces")
    private String lastName;

    @NotBlank(message = "contact number cannot be blank")
    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number")
    private String contactNo;

//    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "(Male|Female|Other)", message = "Gender must must a Male,Female or Other ")
    private String gender;

    private boolean active = true;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;


}
