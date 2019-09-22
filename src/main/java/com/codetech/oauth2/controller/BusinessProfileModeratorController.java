package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.UserBusinessProfileRequestDTO;
import com.codetech.oauth2.model.UserBusinessProfile;
import com.codetech.oauth2.repository.UserBusinessProfileRepository;
import com.codetech.oauth2.service_impl.UserBusinessProfileModeratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:28 PM
 */
@RestController
@RequestMapping("/businessProfileModerator")
public class BusinessProfileModeratorController {

    @Autowired
    UserBusinessProfileRepository userBusinessProfileRepository;

    @Autowired
    UserBusinessProfileModeratorImpl userBusinessProfileModeratorImpl;

    @PostMapping(path = "/moderator")
    public @ResponseBody
    ResponseEntity addUser(@Valid @RequestBody UserBusinessProfileRequestDTO userBusinessProfileRequestDTO) throws ClassNotFoundException {
        return new ResponseEntity<UserBusinessProfile>(userBusinessProfileModeratorImpl.addUserBusinessProfile(userBusinessProfileRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/moderator/{id}")
    public @ResponseBody ResponseEntity DeleteUser(@Valid @PathVariable String id) throws ClassNotFoundException {
        userBusinessProfileModeratorImpl.deleteUserformBusinessProfile(id);
//        return new ResponseEntity<UserBusinessProfile>(userBusinessProfileModeratorImpl.deleteUserformBusinessProfile(id), HttpStatus.CREATED);
        return ResponseEntity.ok("Business profile moderator delete successfully ");
    }


}

