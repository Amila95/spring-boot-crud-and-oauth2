package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.BusinessProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/4/2019
 * Time: 9:42 AM
 */
@Getter
@Setter
public class BusinessProductDTO {
    private Long id;

    @Pattern(regexp = "^(?=.*[a-zA-Z]).{3,40}+$", message = "last name should be have at least one character, values between 3 to 40 and no spaces")
    private String title;

    private String description;

    @JsonIgnore
    BusinessProfile businessProfile;
}
