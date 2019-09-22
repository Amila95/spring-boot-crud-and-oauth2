package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.BusinessProfileDTO;
import com.codetech.oauth2.exceptions.ProfileExceptionHandler;
import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.BusinessRegistionType;
import com.codetech.oauth2.service_impl.BusinessProfileServiceImpl;
import com.codetech.oauth2.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:27 PM
 */

@RestController
@RequestMapping("/businessProfile")
public class BusinessProfileController {

    @GetMapping(path = "/welcome")
    public String welcome(){
        return "profile service up";
    }

    @Autowired
    private BusinessProfileServiceImpl businessProfileServiceImpl;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //create business profile
    @PostMapping(path = "/businessProfiles")
    public @ResponseBody ResponseEntity<?> addProfile(@Valid @RequestBody BusinessProfileDTO businessProfileDTO , BindingResult result, Principal principal) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        if(principal != null) {
            businessProfileServiceImpl.addProfile(businessProfileDTO, principal.getName());
            return ResponseEntity.ok("profile saved successfully...");
        }else{
            throw new ProfileExceptionHandler();
        }
    }

    @PutMapping(path = "/businessProfiles/{id}")
    public @ResponseBody ResponseEntity updateProfile(@Valid @RequestBody BusinessProfileDTO businessProfileDTO  , BindingResult result, @PathVariable String id, Principal principal) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        businessProfileServiceImpl.updateProfile(businessProfileDTO, Long.valueOf(id), principal.getName());
        return ResponseEntity.ok("BusinessProfile successfully updated...");
    }

    //get business profile business profile id
    @GetMapping(path = "/businessProfiles/{id}")
    public @ResponseBody ResponseEntity<BusinessProfile> getProfileById(@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProfileServiceImpl.getProfileById(Long.valueOf(id)));
    }

    @GetMapping(path = "/businessProfilesByUser")
    public List<?> getProfileByUser(Principal principal) throws ClassNotFoundException {
        if(principal != null) {
            return businessProfileServiceImpl.getProfileByUser(principal.getName());
        }else{
            throw new ProfileExceptionHandler();
        }
    }


    @GetMapping(path = "/businessProfiles/all")
    public @ResponseBody ResponseEntity<List> getAllProfiles() throws ClassNotFoundException {
        return ResponseEntity.ok(businessProfileServiceImpl.getAllProfiles()    );
    }



    @PutMapping(path = "/businessProfiles/status/{id}")
    public @ResponseBody ResponseEntity updateProfileStatus(@PathVariable String id) throws ClassNotFoundException {
        businessProfileServiceImpl.updateBusinessProfileByState(id);
        return ResponseEntity.ok("BusinessProfile status successfully updated...");
    }

    @GetMapping("/businessRegistationType")
    public ResponseEntity<List> getRegistation(){
        return ResponseEntity.ok(businessProfileServiceImpl.listBusinessRegistionType());
    }

    @GetMapping("/businessRegistationType/{id}")
    public ResponseEntity<BusinessRegistionType> getRegistationTypeById(@PathVariable String id){
        return ResponseEntity.ok(businessProfileServiceImpl.getBusinessRegistionType(id));
    }







}

