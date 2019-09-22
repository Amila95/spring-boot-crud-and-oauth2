package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.BusinessProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/5/2019
 * Time: 5:23 AM
 */
@Getter
@Setter
public class BusinessServiceDTO {
    private Long id;

    private String title;

    private String description;

    @JsonIgnore
    BusinessProfile businessProfile;
}
