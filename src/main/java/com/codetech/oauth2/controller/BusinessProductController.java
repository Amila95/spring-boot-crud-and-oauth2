package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.BusinessCategoryDTO;
import com.codetech.oauth2.dto.BusinessProductDTO;
import com.codetech.oauth2.model.BusinessCategory;
import com.codetech.oauth2.model.BusinessProduct;
import com.codetech.oauth2.model.Interest;
import com.codetech.oauth2.service_impl.BusinessProductServiceImpl;
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
 * Date: 9/4/2019
 * Time: 9:25 AM
 */
@RestController
@RequestMapping("/businessProduct")
public class BusinessProductController {

    @Autowired
    private BusinessProductServiceImpl businessProductServiceImpl;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //add business product
    //id equal business profile
    @PostMapping("/businessProduct/{id}")
    public ResponseEntity<?> saveBusinessCategory(@Valid @RequestBody BusinessProductDTO businessProductDTO , BindingResult result, @PathVariable String id, Principal principal) throws ClassNotFoundException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        return ResponseEntity.ok(businessProductServiceImpl.addBusinessCategory(businessProductDTO,id,principal.getName()));
    }

    //update business product
    //id equal business product
    @PutMapping("/businessProduct/{id}")
    public ResponseEntity<BusinessProductDTO> updateBusinessCategory(@Valid @RequestBody BusinessProductDTO businessProductDTO, @PathVariable String id, Principal principal) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProductServiceImpl.updateBusinessCategory(businessProductDTO,id,principal.getName()));
    }

    //get business product by business_product_id
    @GetMapping("/businessProduct/{id}")
    public ResponseEntity<BusinessProductDTO> getBusinessCategory(@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProductServiceImpl.getBusinessProductByBusinessProductId(id));
    }

    //get All business product
    @GetMapping("/businessProduct")
    public ResponseEntity<List> getAllBusinessCategory() throws ClassNotFoundException {
        return ResponseEntity.ok(businessProductServiceImpl.getAllBusinessProduct());
    }

    //get business product by business_profile_id
    @GetMapping("/businessProductByBusinessProfile/{id}")
    public ResponseEntity<List> getBusinessCategoryByBusinessId(@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessProductServiceImpl.getBusinessProductByBusinessProfileId(id));
    }


}
