package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.UserDTO;
import com.codetech.oauth2.model.Interest;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.service_impl.UserInterestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/28/2019
 * Time: 9:21 AM
 */

@RestController
@RequestMapping("/interest")
public class UserInterestController {

    @Autowired
    UserInterestServiceImpl userInterestServiceImpl;


    @GetMapping("/userInterest")
    public ResponseEntity<List> getCategory(){
        return ResponseEntity.ok(userInterestServiceImpl.listUserInterest());
    }

    //add user interest
    @PostMapping("/userInterest")
    public ResponseEntity<UserDTO> saveUserInterest(@Valid @RequestBody Set<Interest> interest, Principal principal) throws ClassNotFoundException {
        return ResponseEntity.ok(userInterestServiceImpl.addInterestToUser(interest, principal.getName()));
    }

//    //remove user interest
//    @DeleteMapping("/userInterest")
//    public ResponseEntity<UserDTO> deleteUserInterest(@Valid @RequestBody Set<Interest> interest, Principal principal) throws ClassNotFoundException {
//        return ResponseEntity.ok(userInterestServiceImpl.removeInterestToUser(interest, principal.getName()));
//    }

}
