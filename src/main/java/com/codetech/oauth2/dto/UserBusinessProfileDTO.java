package com.codetech.oauth2.dto;

import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:39 PM
 */

@Getter
@Setter
public class UserBusinessProfileDTO {

    User user;
    BusinessProfile businessProfile;
    String role;

}
