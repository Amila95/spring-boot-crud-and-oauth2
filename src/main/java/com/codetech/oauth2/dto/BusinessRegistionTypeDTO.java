package com.codetech.oauth2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/26/2019
 * Time: 8:23 PM
 */
@Getter
@Setter
public class BusinessRegistionTypeDTO {
    private Long id;

    @Pattern(regexp = "^(?=.*[a-zA-Z]).{3,40}+$", message = "business registion type name should be have at least one character, values between 3 to 40 and no spaces")
    private String name;

}
