package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/27/2019
 * Time: 12:57 PM
 */
@Getter
@Setter
@Entity
public class Address extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "line_1")
//    @NotBlank(message = "line1 cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z\\s]).{3,40}+$", message = "Line 1 should be have at least one character, values between 3 to 40 ")
    private String line1;

    @Column(name = "line_2")
    @Pattern(regexp = "^(?=.*[a-zA-Z\\s]).{3,40}+$", message = "Line 2 should be have at least one character, values between 3 to 40 ")
//    @NotBlank(message = "line2 cannot be blank")
    private String line2;

    @Pattern(regexp = "^(?=.*[a-zA-Z\\s]).{3,40}+$", message = "Line 3 should be have at least one character, values between 3 to 40 ")
    @Column(name = "line_3")
    private String line3;

    @Column(name = "city")
    @NotBlank(message = "city cannot be blank")
//    @Pattern(regexp = "^[a-zA-Z0-9_ ]{1,20}+$", message = "invalid city name")
    @Pattern(regexp = "^(?=.*[a-zA-Z\\s]).{3,40}+$", message = "City should be have at least one character, values between 3 to 40 ")
    private String city;

    @Column(name = "country")
    @NotBlank(message = "country cannot be blank")
    @Pattern(regexp = "^(?=.*[a-zA-Z\\s]).{3,40}+$", message = "Country should be have at least one character, values between 3 to 40 ")
    private String country;

    @Column(name = "zip_code")
    @NotBlank(message = "zipCode cannot be blank")
    @Size(min = 4, max = 6, message
            = "zip code must be between 4 and 6 integers")
    @Pattern(regexp = "^[0-9]{4,6}+$", message = "zip code should be number")
    private String zipCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="business_id", nullable = false)
    @JsonIgnore
    private BusinessProfile businessProfile;


}
