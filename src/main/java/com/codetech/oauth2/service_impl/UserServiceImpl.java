package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.InterestDTO;
import com.codetech.oauth2.dto.UserDTO;
import com.codetech.oauth2.dto.UserProfileDTO;
import com.codetech.oauth2.exceptions.ParameterErrorException;
import com.codetech.oauth2.exceptions.UserNotFoundExceptionHandler;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.validator.IsNumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:09 PM
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IsNumeric isNumeric;
    @Autowired
    private TransformServiceImpl transformService;

    public Iterable<UserDTO> allUsers() throws ClassNotFoundException {
        Iterable users = userRepository.findAll();
        Iterable<UserDTO> userDTOS = (Iterable<UserDTO>) transformService.convertToDtoList(users, Constant.USER_DTO_CLASS);
//        userDTOS.forEach(category ->
//                category.setUserProfileDTO((UserProfileDTO) transformService.convertToDto(users.getUserProfile(),Constant.USER_PROFILE_DTO_CLASS)););
//        businessProfileDTO.getBusinessCategorySet().forEach(category -> {if(!businessCategoryRepository.findById(category.getId()).isPresent()  ) {
//            throw new BusinessCategoryNotFoundExceptionHandler();}}
//        );
//        userDTO.setInterestDTOS((Set<InterestDTO>) interestDTO);
//        userDTO.setUserProfileDTO((UserProfileDTO) transformService.convertToDto(user1.getUserProfile(),Constant.USER_PROFILE_DTO_CLASS));

        return userDTOS;

//        return new ResponseEntity<UserDTO >((UserDTO) transformService.convertToDto(newUser, Constant.USER_DTO_CLASS), HttpStatus.CREATED);

    }

    public String deactiveUserByAdmin(String id)  {
        if(isNumeric(id)== true) {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            if (user.isPresent()) {
                user.get().setAdminActive(!user.get().isAdminActive());
                userRepository.saveAndFlush(user.get());
                return "This user deactive By admin";
            } else {
                throw new UserNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

    public String deactiveUserByUser(String name)  {
        User user = userRepository.findByUsername(name);
        if(user != null) {
            user.setUserActive(!user.isUserActive());
            userRepository.deletetoken(user.getUsername());
            userRepository.saveAndFlush(user);
            return "This user deactive  by user";
        }else {
            throw new UserNotFoundExceptionHandler();
        }
    }

    public void logout(String name)  {
        User user = userRepository.findByUsername(name);
        if(user != null) {
            userRepository.deletetoken(user.getUsername());
        }else {
            throw new UserNotFoundExceptionHandler();
        }
    }

    public Optional<User> getUser(String id) {
        if(isNumeric(id)== true) {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            if (user.isPresent()) {
                return user;
            } else {
                throw new UserNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }


}

