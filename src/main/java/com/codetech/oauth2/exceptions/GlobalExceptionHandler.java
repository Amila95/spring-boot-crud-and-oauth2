package com.codetech.oauth2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by A Dasitha.
 * Date: 7/8/2019
 * Time: 10:48 AM
 */


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidLoginResponse.class)
    public ResponseEntity<Object> invalidLoginResponse() {
        return new ResponseEntity<>("User name not found...", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProfileAlreadyExistExceptionHandler.class)
    public ResponseEntity<Object> profileAlreadyExistException() {
        return new ResponseEntity<Object>("This BusinessProfile is already exist...", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProfileExceptionHandler.class)
    public ResponseEntity<Object> profileNotFoundException() {
        return new ResponseEntity<Object>("The profile is not found...", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidUrlExceptionHandler.class)
    public ResponseEntity<Object> invalidUrlException() {
        return new ResponseEntity<Object>("Invalid Url", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailIdAlreadyExistExceptionHandler.class)
    public ResponseEntity<Object> emailIdAlreadyExistException() {
        return new ResponseEntity<Object>("Email id already exist...", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessProfileNotFoundExceptionHandler.class)
    public ResponseEntity<Object> businessProfileNotFoundException() {
        return new ResponseEntity<Object>("Business profile not found..", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UserNotFoundExceptionHandler.class)
    public ResponseEntity<Object> userNotFoundException() {
        return new ResponseEntity<Object>("user not found..", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UserProfileAllReadyExcepationHandler.class)
    public ResponseEntity<Object> userProfileAlreadyException() {
        return new ResponseEntity<Object>("user profile already in ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<Object> numberFormatException() {
        return new ResponseEntity<Object>("Parameter must be a numbers", HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(value = ParameterErrorException.class)
    public ResponseEntity<Object> ParameterFormatException() {
        return new ResponseEntity<Object>("Parameter not matching", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = RoleEmptyExceptionHandler.class)
    public ResponseEntity<Object> RoleEmptyException() {
        return new ResponseEntity<Object>("Role is empty ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UserBusinessProfileAlreadyExistException.class)
    public ResponseEntity<Object> UserBusinessProfileAlreadyExection() {
        return new ResponseEntity<Object>("That User already in this business Profile ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessCategoryNotFoundExceptionHandler.class)
    public ResponseEntity<Object> businessCategoryNotFoundExection() {
        return new ResponseEntity<Object>("That business category not found", HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(value = BusinessRegistationTypeNotFoundExceptionHandler.class)
    public ResponseEntity<Object> BusinessRegistationTypeNotFoundExceptionHandler() {
        return new ResponseEntity<Object>("That business registation type not found", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessAddressAlreayExceptionHandler.class)
    public ResponseEntity<Object> BusinessAddressAlreayExceptionHandler() {
        return new ResponseEntity<Object>("That business Address Already in there", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessAddressNotFoundExeceptionHandler.class)
    public ResponseEntity<Object> BusinessAddressNotFoundExeceptionHandler() {
        return new ResponseEntity<Object>("That business Address not found", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = UserInterestNotFoundExceptionHandler.class)
    public ResponseEntity<Object> UserInterestNotFoundExceptionHandler() {
        return new ResponseEntity<Object>("User interest not found ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NullableValuesIsFound.class)
    public ResponseEntity<Object> findNullable() {
        return new ResponseEntity<Object>("Nullable value is found in input field ", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = BusinessProductNotFoundExceptionHandler.class)
    public ResponseEntity<Object> BusinessProductNotFoundExceptionHandler() {
        return new ResponseEntity<Object>("Business Product not found ", HttpStatus.NOT_ACCEPTABLE);
    }




    @ExceptionHandler(value = BusinessProductPremitionExcepationHandler.class)
    public ResponseEntity<Object> BusinessProductPremitionHandler() {
        return new ResponseEntity<Object>("This Business Product not permition this user ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessServiceNotFoundExceptionHandler.class)
    public ResponseEntity<Object> BusinessServiceNotFoundExceptionHandler() {
        return new ResponseEntity<Object>("Business Service not found ", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BusinessServicePremitionHandler.class)
    public ResponseEntity<Object> BusinessServicePremitionHandler() {
        return new ResponseEntity<Object>("This Business Service not permition this user ", HttpStatus.NOT_ACCEPTABLE);
    }



    @ExceptionHandler(value = BranchNotFoundExceptionHandler.class)
    public ResponseEntity<Object> BranchNotFoundExceptionHandler(){
        return new ResponseEntity<Object> ("Branch not found",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NumericalValueNotFoundExceptionHandler.class)
    public ResponseEntity<Object> NumericalValueNotFoundExceptionHandler(){
        return new ResponseEntity<Object>("Enter a valid number..!",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = BranchAlreadyDeletedExceptionHandler.class)
    public ResponseEntity<Object> BranchAlreadyDeletedExceptionHandler(){
        return new ResponseEntity<Object>("This Branch is already deleted.",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = MiddleNameValidationExcepationHandeler.class)
    public ResponseEntity<Object> MiddleNameValidationExcepationHandeler(){
        return new ResponseEntity<Object>("middle name should be have at least one character, values between 3 to 40 and no spaces",HttpStatus.NOT_ACCEPTABLE);
    }

}
