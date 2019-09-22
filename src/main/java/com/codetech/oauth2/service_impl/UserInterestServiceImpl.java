package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BusinessProfileDTO;
import com.codetech.oauth2.dto.InterestDTO;
import com.codetech.oauth2.dto.UserDTO;
import com.codetech.oauth2.dto.UserProfileDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.Interest;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.InterestRepository;
import com.codetech.oauth2.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codetech.oauth2.validator.IsNullable.isNullable;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/28/2019
 * Time: 9:30 AM
 */
@Service
public class UserInterestServiceImpl {
    @Autowired
    public InterestRepository interestRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private TransformServiceImpl transformService;




    public InterestDTO addUserInterest(InterestDTO interestDTO) {
        try {
            if (isNullable(interestDTO.getName())) {
                throw new NullableValuesIsFound();
            }
            Interest interest = new Interest();
            interest.setName(interestDTO.getName());
            return (InterestDTO) transformService.convertToDto(interestRepository.save(interest), Constant.INTEREST_DTO_CLASS);


        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Interest already exists");
        }
    }



    public List<Interest> listUserInterest(){
        List<Interest> getAllInterest = interestRepository.findAll();
        return getAllInterest;
    }

    public UserDTO addInterestToUser(Set<Interest> interest, String name) throws ClassNotFoundException {
        User user = userRepository.findByUsername(name);
        if(user != null) {
            interest.forEach(category -> {
                        if (category.getId() == null) {
                            throw new UserInterestNotFoundExceptionHandler();
                        }
                    }
            );
            interest.forEach(category -> {
                        if (!interestRepository.findById(category.getId()).isPresent()) {
                            throw new UserInterestNotFoundExceptionHandler();
                        }
                    }
            );
            Set<Interest> interests = user.getInterests();
            interests.addAll(interest);
            user.setInterests(interests);
            User user1 = userRepository.save(user);

            HashSet<InterestDTO> interestDTO = (HashSet<InterestDTO>) transformService.convertToDtoHashSet(interests, Constant.INTEREST_DTO_CLASS);

            UserDTO userDTO = (UserDTO) transformService.convertToDto(user1, Constant.USER_DTO_CLASS);
//            userDTO.setInterest((Set<Interest>) interestDTO);
//            userDTO.setUserProfile((UserProfileDTO) transformService.convertToDto(user1.getUserProfile(),Constant.USER_PROFILE_DTO_CLASS));

            return  userDTO;

        }else{
            throw new UserNotFoundExceptionHandler();
        }

    }

//    public UserDTO removeInterestToUser(Set<Interest> interest, String name) throws ClassNotFoundException {
//        User user = userRepository.findByUsername(name);
//        if(user != null) {
//            Set<Interest> interests = user.getInterests();
//            if(interests.equals(interest)) {
//                interests.clear();
//            }
//            interests.removeAll(interest);
//
//            User user1 = userRepository.saveAndFlush(user);
//
//            HashSet<InterestDTO> interestDTO = (HashSet<InterestDTO>) transformService.convertToDtoHashSet(interests, Constant.INTEREST_DTO_CLASS);
//
//            UserDTO userDTO = (UserDTO) transformService.convertToDto(user1, Constant.USER_DTO_CLASS);
//            userDTO.setInterestDTOS((Set<InterestDTO>) interestDTO);
//            userDTO.setUserProfileDTO((UserProfileDTO) transformService.convertToDto(user1.getUserProfile(),Constant.USER_PROFILE_DTO_CLASS));
//
//            return  userDTO;
//
//        }else{
//            throw new UserNotFoundExceptionHandler();
//        }
//
//    }

    public Interest updateUserInterest(Interest interest, String id) {
        if(interestRepository.findById(Long.valueOf(id)).isPresent()){
            try {
            Interest interest1 = interestRepository.findById(Long.valueOf(id)).get();
            interest1.setName(interest.getName());
            return interestRepository.saveAndFlush(interest1);
            }catch (Exception e){
                throw new UsernameAlreadyExistsException("Interest already exists");
            }
        }

        throw new UserInterestNotFoundExceptionHandler();
    }
}
