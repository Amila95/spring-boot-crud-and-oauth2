package com.codetech.oauth2.controller;

import com.codetech.oauth2.dto.BusinessProductDTO;
import com.codetech.oauth2.dto.BusinessServiceDTO;
import com.codetech.oauth2.model.BusinessService;
import com.codetech.oauth2.service_impl.BusinessServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/5/2019
 * Time: 5:24 AM
 */

@RestController
@RequestMapping("/businessService")
public class BusinessServiceController {

    @Autowired
    private BusinessServiceServiceImpl businessServiceServiceImpl;

    //add business service
    //id equal business profile
    @PostMapping("/businessService/{id}")
    public ResponseEntity<?> saveBusinessService(@Valid @RequestBody BusinessServiceDTO businessServiceDTO, @PathVariable String id, Principal principal) throws ClassNotFoundException {
        return ResponseEntity.ok(businessServiceServiceImpl.addBusinessService(businessServiceDTO,id,principal.getName()));
    }

    @PutMapping("/businessService/{id}")
    public ResponseEntity<BusinessServiceDTO> updateBusinessCategory(@Valid @RequestBody BusinessServiceDTO businessServiceDTO, @PathVariable String id, Principal principal) throws ClassNotFoundException {
        return ResponseEntity.ok(businessServiceServiceImpl.updateBusinessService(businessServiceDTO,id,principal.getName()));
    }

//    get business product by business_service_id
    @GetMapping("/businessService/{id}")
    public ResponseEntity<BusinessServiceDTO> getBusinessCategory(@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessServiceServiceImpl.getBusinessServiceByBusinessServiceId(id));
    }

    //get All business service
    @GetMapping("/businessService")
    public ResponseEntity<List> getAllBusinessCategory() throws ClassNotFoundException {
        return ResponseEntity.ok(businessServiceServiceImpl.getAllBusinessService());
    }

    //get business product by business_profile_id
    @GetMapping("/businessServiceByBusinessProfile/{id}")
    public ResponseEntity<List> getBusinessCategoryByBusinessId(@PathVariable String id) throws ClassNotFoundException {
        return ResponseEntity.ok(businessServiceServiceImpl.getBusinessServiceByBusinessProfileId(id));
    }


}
