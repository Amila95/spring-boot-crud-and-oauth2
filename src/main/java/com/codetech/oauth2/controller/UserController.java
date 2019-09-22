package com.codetech.oauth2.controller;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.UserDTO;
import com.codetech.oauth2.dto.UserNameDTO;
import com.codetech.oauth2.exceptions.UserNotFoundExceptionHandler;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.service_impl.TransformServiceImpl;
import com.codetech.oauth2.service_impl.UserServiceImpl;
import com.codetech.oauth2.services.MapValidationErrorService;
import com.codetech.oauth2.services.UserService;
import com.codetech.oauth2.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 12:29 PM
 */
@RestController
@RequestMapping(path = "/user-service")
public class UserController {
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransformServiceImpl transformService;


    @GetMapping(path = "/get-all-users")
    //@CrossOrigin(origins = "http://localhost:3000")
    public
    ResponseEntity<Iterable> getAllUsers(Principal principal) {

        //System.out.println("xxxxxxxxxx= " +principal.getName());
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/view-user")
    public @ResponseBody ResponseEntity<Optional> viewUser(@RequestParam("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping(path="/userByUsername")
    public @ResponseBody Boolean viewUserByUsername(@Valid @RequestBody UserNameDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername())!= null){
            return true;
        }

        return false;
    }

    @GetMapping(path = "/logout")
    public @ResponseBody ResponseEntity<String> logout(Principal principal) throws ServletException {
       if(principal != null){
           userServiceImpl.logout(principal.getName());
           return ResponseEntity.ok("successfully logout");
       }else{
           throw new UserNotFoundExceptionHandler();
       }


    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result)throws ClassNotFoundException{
        // Validate passwords match

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        System.out.println("registration called");


        User newUser = userService.saveUser(user);


         return new ResponseEntity<UserDTO > ((UserDTO) transformService.convertToDto(newUser, Constant.USER_DTO_CLASS),HttpStatus.CREATED);
    }


    //deactivate user by user
    @PutMapping("/user")
    public ResponseEntity<?> deactivateUserByUser(Principal principal){
        if(principal != null) {
            return ResponseEntity.ok(userServiceImpl.deactiveUserByUser(principal.getName()));
        }else{
            throw new UserNotFoundExceptionHandler();
        }
    }

}
