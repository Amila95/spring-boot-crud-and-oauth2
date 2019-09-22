package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.UserBusinessProfileDTO;
import com.codetech.oauth2.dto.UserBusinessProfileRequestDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.model.UserBusinessProfile;
import com.codetech.oauth2.repository.BusinessProfileRepository;
import com.codetech.oauth2.repository.UserBusinessProfileRepository;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.validator.IsNumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:08 PM
 */
@Service
public class UserBusinessProfileModeratorImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusinessProfileRepository businessProfileRepository;

    @Autowired
    UserBusinessProfileRepository userBusinessProfileRepository;

    @Autowired
    private TransformServiceImpl transformService;

    @Autowired
    private IsNumeric isNumeric;

    public UserBusinessProfile addUserBusinessProfile(UserBusinessProfileRequestDTO userBusinessProfileRequestDTO) throws ClassNotFoundException {
        if(IsNumeric.isNumeric(userBusinessProfileRequestDTO.getBusinessId())== true && IsNumeric.isNumeric(userBusinessProfileRequestDTO.getUserId())== true ){
            Optional<User> user = userRepository.findById(Long.valueOf(userBusinessProfileRequestDTO.getUserId()));
            if (user.isPresent()) {
                Optional<BusinessProfile> businessProfile = businessProfileRepository.findById(Long.valueOf(userBusinessProfileRequestDTO.getBusinessId()));
                if (businessProfile.isPresent()) {
                    if ((userBusinessProfileRepository.existsByBusinessProfileAndUser(businessProfile, user)) )
                        throw new UserBusinessProfileAlreadyExistException();
                    UserBusinessProfileDTO userBusinessProfileDTO = new UserBusinessProfileDTO();
                    userBusinessProfileDTO.setRole(userBusinessProfileRequestDTO.getRole());
                    userBusinessProfileDTO.setUser(user.get());
                    userBusinessProfileDTO.setBusinessProfile(businessProfile.get());
                    UserBusinessProfile userBusinessProfile = (UserBusinessProfile) transformService.convertToEntity(userBusinessProfileDTO, Constant.USER_BUSINESS_PROFILE_ENTITY_CLASS);
                    return userBusinessProfileRepository.save(userBusinessProfile);
                } else {
                    throw new BusinessProfileNotFoundExceptionHandler();
                }
            } else {
                throw new UserNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

    public UserBusinessProfile deleteUserformBusinessProfile(String id){
        if(IsNumeric.isNumeric(id) == true) {
            Optional<UserBusinessProfile> userBusinessProfile = userBusinessProfileRepository.findById(Long.valueOf(id));
            if (userBusinessProfile.isPresent()) {
                userBusinessProfile.get().setActive(false);
                return userBusinessProfileRepository.saveAndFlush(userBusinessProfile.get());
            } else {
                throw new ProfileExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

}

