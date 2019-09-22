package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.*;
import com.codetech.oauth2.model.*;
import com.codetech.oauth2.repository.BusinessRegistionTypeRepository;
import com.codetech.oauth2.service_impl.BusinessCategoryServiceImpl;
import com.codetech.oauth2.service_impl.BusinessProfileServiceImpl;
import com.codetech.oauth2.service_impl.UserInterestServiceImpl;
import com.codetech.oauth2.service_impl.UserServiceImpl;
import com.codetech.oauth2.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:27 PM
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    BusinessProfileServiceImpl businessProfileServiceImpl;

    @Autowired
    BusinessCategoryServiceImpl businessCategoryServiceImpl;

    @Autowired
    UserInterestServiceImpl userInterestServiceImpl;

    @Autowired
    BusinessRegistionTypeRepository businessRegistionTypeRepository;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //get all users
    @GetMapping("/getAllUser")
    public ResponseEntity<Iterable<UserDTO>> allUsers() throws ClassNotFoundException {
        return ResponseEntity.ok(userServiceImpl.allUsers());
    }

    //get businessProfile userId
    @GetMapping(path = "/businessProfilesByUserId/{id}")
    public ResponseEntity<List<?>> getProfileByUser(@PathVariable String id) throws ClassNotFoundException {
        return  ResponseEntity.ok(businessProfileServiceImpl.getProfileByUserId(id));
    }

    //business profile active
    @PutMapping("/businessProfileCompletionStatus/{id}")
    public ResponseEntity<BusinessProfile> updateBusinessProfileByState(@PathVariable String id){
        return ResponseEntity.ok(businessProfileServiceImpl.updateBusinessProfileByState(id));
    }

    //deactivate user by admin
    @PutMapping("/user/{id}")
    public ResponseEntity<?> deactivateUserByAdmin(@PathVariable String id){
        return ResponseEntity.ok(userServiceImpl.deactiveUserByAdmin(id));
    }

    //get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@Valid @PathVariable String id){
        return ResponseEntity.ok(userServiceImpl.getUser(id));
    }

    //add business category
    @PostMapping("/businessCategory")
    public ResponseEntity<?> saveBusinessCategory(@Valid @RequestBody BusinessCategoryDTO businessCategoryDTO, BindingResult result) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        return ResponseEntity.ok(businessCategoryServiceImpl.addBusinessCategory(businessCategoryDTO));
    }

    //update business category
    @PutMapping("/businessCategory/{id}")
    public ResponseEntity<BusinessCategory> updateBusinessCategory(@Valid @RequestBody BusinessCategory businessCategory, @PathVariable String id){
        return ResponseEntity.ok(businessCategoryServiceImpl.updateBusinessCategory(businessCategory,id));
    }


    //business registation type
    @PostMapping("/businesssRegistionType")
    public ResponseEntity<?> saveBusinessRegistionType(@Valid @RequestBody BusinessRegistionTypeDTO businessRegistionTypeDTO, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        return ResponseEntity.ok(businessCategoryServiceImpl.addBusinessRegistionType(businessRegistionTypeDTO));
    }

    //update registation type
    @PutMapping("/businesssRegistionType/{id}")
    public ResponseEntity<?> updateBusinessRegistionType(@Valid @RequestBody BusinessRegistionTypeDTO businessRegistionTypeDTO, BindingResult result , @PathVariable String id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        return ResponseEntity.ok(businessCategoryServiceImpl.updateBusinessRegistionType(businessRegistionTypeDTO,id));
    }

    //add user interest
    @PostMapping("/userInterest")
    public ResponseEntity<?> saveUserInterest(@Valid @RequestBody InterestDTO interestDTO, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        return ResponseEntity.ok(userInterestServiceImpl.addUserInterest(interestDTO));
    }

    //update user interest
    @PutMapping("/userInterest/{id}")
    public ResponseEntity<Interest> updateUserInterest(@Valid @RequestBody Interest interest, @PathVariable String id){
        return ResponseEntity.ok(userInterestServiceImpl.updateUserInterest(interest, id));
    }


}

