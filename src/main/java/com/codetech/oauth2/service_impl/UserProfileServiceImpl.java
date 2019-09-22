package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.UserProfileDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.model.UserProfile;
import com.codetech.oauth2.repository.UserProfileRepository;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.validator.IsNumeric;
import com.codetech.oauth2.validator.MiddleNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:09 PM
 */
@Service
public class UserProfileServiceImpl {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    private TransformServiceImpl transformService;
    @Autowired
    private IsNumeric isNumeric;
    @Autowired
    private MiddleNameValidator middleNameValidator;

    public UserProfileDTO saveUserProfile(UserProfileDTO userProfileDTO, String userName) throws ClassNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user != null) {
            UserProfile profile1 = userProfileRepository.findByUser(user);
            if (profile1 != null) {
                throw new UserProfileAllReadyExcepationHandler();
            } else {
//                if(!middleNameValidator.validMiddleName(userProfileDTO.getMiddleName())){
//                        throw new MiddleNameValidationExcepationHandeler();
//                }
                UserProfile userProfile = (UserProfile) transformService.convertToEntity(userProfileDTO, Constant.USER_PROFILE_ENTITY_CLASS);
                userProfile.setUser(user);
                user.setProfileCompletion(true);
                return (UserProfileDTO) transformService.convertToDto(userProfileRepository.save(userProfile), Constant.USER_PROFILE_DTO_CLASS);
            }
        }else{
            throw new UserNotFoundExceptionHandler();
        }
    }

    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO, String userName) throws ClassNotFoundException {
        User user = userRepository.findByUsername(userName);
        UserProfile profile1 = userProfileRepository.findByUser(user);
        if(profile1 != null) {
            if (userProfileRepository.existsById(profile1.getId())) {
//            userProfileDTO.setId(profile1.getId());
//                if(!middleNameValidator.validMiddleName(userProfileDTO.getMiddleName())){
//                    throw new MiddleNameValidationExcepationHandeler();
//                }
                UserProfile userProfile = (UserProfile) transformService.convertToEntity(userProfileDTO, Constant.USER_PROFILE_ENTITY_CLASS);
                userProfile.setUser(user);
                userProfile.setId(profile1.getId());
                return (UserProfileDTO) transformService.convertToDto(userProfileRepository.saveAndFlush(userProfile), Constant.USER_PROFILE_DTO_CLASS);
            } else {
                throw new ProfileExceptionHandler();
            }
        }else{
            throw new ProfileExceptionHandler();
        }
    }

    public Optional<UserProfile> getUserProfile(String id) throws ClassNotFoundException{
        if(isNumeric(id)== true) {
            Optional<UserProfile> userProfile = userProfileRepository.findById(Long.valueOf(id));
            if (userProfile.isPresent()) {
//            return (UserProfileDTO) transformService.convertToDto(userProfile, Constant.USER_PROFILE_DTO_CLASS);
                return userProfile;
            } else {
                throw new ProfileExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }

    }

    public UserProfileDTO getUserProfileBySelf(String name) throws ClassNotFoundException {
        User user = userRepository.findByUsername(name);
        if(user != null){
            UserProfile userProfile = userProfileRepository.findByUser(user);
            if(userProfile != null) {
                return (UserProfileDTO) transformService.convertToDto(userProfile, Constant.USER_PROFILE_DTO_CLASS);
            }else{
                throw new ProfileExceptionHandler();
            }
//            return  new ResponseEntity<UserProfile>(userProfile, HttpStatus.CREATED);
        }else{
            throw new UserNotFoundExceptionHandler();
        }
    }

    public void updateUser(String userName){
        User user = userRepository.findByUsername(userName);
        user.setProfileCompletion(true);
        userRepository.save(user);
    }

    public void deactiveProfile(String userName){
        User user = userRepository.findByUsername(userName);
        if(user != null) {
            UserProfile userProfile = userProfileRepository.findByUser(user);
            if (userProfile != null) {
                userProfile.setActive(false);
                userProfileRepository.save(userProfile);
            } else {
                throw new ProfileExceptionHandler();
            }
        }else{
            throw new ProfileExceptionHandler();
        }
    }


}

