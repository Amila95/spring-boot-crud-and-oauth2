package com.codetech.oauth2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * Created by Supun Dilshan.
 * Date: 09/04/2019
 * Time: 09:46 PM
 */
@Getter
@Setter
@Entity
public class Branch extends  Auditable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The location cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9_ ]{1,20}+$", message = "Invalid location name")
    private String location;

    @NotBlank(message = "This field cannot be empty")
    @Pattern(regexp = "(\\+94|0)[0-9]{9}", message = "invalid contact number") //Only Sri Lanka
    private String contactNo;

    //activate/deactivate status
    private boolean state = true;

    //mapping
    @ManyToOne
    @JoinColumn(name = "businessProfile")
    BusinessProfile businessProfiles;
}
