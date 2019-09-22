package com.codetech.oauth2.controller;

import com.codetech.oauth2.model.Address;
import com.codetech.oauth2.service_impl.BusinessProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/27/2019
 * Time: 1:33 PM
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    BusinessProfileServiceImpl businessProfileServiceImpl;
    //add address to business profile
    @PostMapping("/address/{id}")
    public ResponseEntity<?> saveUserProfile(@Valid @RequestBody Address address, BindingResult result ,@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProfileServiceImpl.addAddress(address, id));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody Address address, BindingResult result ,@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProfileServiceImpl.updateAddress(address, id));

    }
}
