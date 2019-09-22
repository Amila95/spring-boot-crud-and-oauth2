package com.codetech.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 3:31 PM
 */

@RestController
public class WelcomeController {
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('Admin')")
    public String allUsers(){
        return "This For admin";
    }
}
