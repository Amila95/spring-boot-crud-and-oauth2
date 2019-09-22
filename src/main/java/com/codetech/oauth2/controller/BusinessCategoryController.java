package com.codetech.oauth2.controller;

import com.codetech.oauth2.model.BusinessCategory;
import com.codetech.oauth2.service_impl.BusinessCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/23/2019
 * Time: 1:13 PM
 */
@RestController
@RequestMapping("/businessCategory")
public class BusinessCategoryController {

    @Autowired
    BusinessCategoryServiceImpl businessCategoryServiceImpl;

    @GetMapping("/businessCategory")
    public ResponseEntity<List> getCategory(){
        return ResponseEntity.ok(businessCategoryServiceImpl.listBusinessCategory());
    }

    @GetMapping("/businessCategory/{id}")
    public ResponseEntity<BusinessCategory> getCategory(@PathVariable String id){
        return ResponseEntity.ok(businessCategoryServiceImpl.BusinessCategoryById(id));
    }

}
