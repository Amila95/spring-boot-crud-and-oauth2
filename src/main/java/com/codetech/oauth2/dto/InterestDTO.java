package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/31/2019
 * Time: 7:00 AM
 */
@Getter
@Setter
public class InterestDTO {
    private Long id;

//    ^((?:[A-Za-z0-9-'.,@:?!()$#/\\]+|&[^#])*&?)
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9_ ].{3,40}+$", message = "name should be have at least one character, values between 3 to 40 and no spaces")
    private String name;

    @Getter(onMethod = @__( @JsonIgnore))
    private Set<User> users = new HashSet<User>();
}
