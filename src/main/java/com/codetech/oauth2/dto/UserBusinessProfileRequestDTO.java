package com.codetech.oauth2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:41 PM
 */
@Getter
@Setter
public class UserBusinessProfileRequestDTO {
    @NotBlank(message = "user id required")
    private String userId;
    @NotBlank(message = "business id id required")
    private  String businessId;
    @NotBlank(message = "role is id required")
    private String role;
}
