package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.UserProfileDTO;
import com.codetech.oauth2.exceptions.UserNotFoundExceptionHandler;
import com.codetech.oauth2.repository.UserProfileRepository;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.service_impl.UserProfileServiceImpl;
import com.codetech.oauth2.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:30 PM
 */
@RestController

@RequestMapping("/userProfile")
public class UserProfileController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileServiceImpl userProfileServiceImpl;



    @GetMapping("/welcome/userprofile")
    public String welcome(){
        return "user profile controller working";
    }

    //add user profile
    @PostMapping("/userProfile")
    public ResponseEntity<?> saveUserProfile (@Valid @RequestBody UserProfileDTO userProfileDTO, BindingResult result, Principal principal) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        if(principal != null) {
            UserProfileDTO profile = userProfileServiceImpl.saveUserProfile(userProfileDTO, principal.getName());
            return new ResponseEntity<UserProfileDTO>(profile, HttpStatus.CREATED);
        }else{
            throw new UserNotFoundExceptionHandler();

        }
    }

    //Update User Profile
    @PutMapping("/userProfile")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserProfileDTO userProfileDTO, BindingResult result, Principal principal) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        if(principal != null) {
            UserProfileDTO profile = userProfileServiceImpl.updateUserProfile(userProfileDTO, principal.getName());
            return new ResponseEntity<UserProfileDTO>(profile, HttpStatus.CREATED);
        }else{
            throw new UserNotFoundExceptionHandler();
        }
    }



    //retrieve user profile by user profile id
    @GetMapping("/userProfile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable String id)throws ClassNotFoundException{
//        Optional<UserProfile> userProfile = userProfileRepository.findById(Long.valueOf(id));
        return  new ResponseEntity<>(userProfileServiceImpl.getUserProfile(id), HttpStatus.CREATED);
    }

    //retrieve user profile of logged user
    @GetMapping("/userProfile")
    public UserProfileDTO getUserProfileBySelf(Principal principal) throws ClassNotFoundException {
        if(principal != null){
            return userProfileServiceImpl.getUserProfileBySelf(principal.getName());
        }else{
            throw new UserNotFoundExceptionHandler();
        }

    }

    //profile completion status update by self in front-end
    @PutMapping("/profileCompletionStatus")
    public ResponseEntity<?> updateByState(Principal principal){
        if(principal != null) {
            userProfileServiceImpl.updateUser(principal.getName());
            return new ResponseEntity<String>("Update Succeful",HttpStatus.CREATED);
        }else{
            throw new UserNotFoundExceptionHandler();
        }

    }

    //delete user profile by self
    @DeleteMapping ("/userProfile")
    public ResponseEntity<?> deleteProfile(Principal principal){
        if(principal != null) {
            userProfileServiceImpl.deactiveProfile(principal.getName());
            return new ResponseEntity<String>("Delete SuceccFully ",HttpStatus.CREATED);
        }else{
            throw new UserNotFoundExceptionHandler();
        }
    }


}

