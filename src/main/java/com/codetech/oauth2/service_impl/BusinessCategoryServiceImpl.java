package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BusinessCategoryDTO;
import com.codetech.oauth2.dto.BusinessRegistionTypeDTO;
import com.codetech.oauth2.exceptions.BusinessCategoryNotFoundExceptionHandler;
import com.codetech.oauth2.exceptions.BusinessRegistationTypeNotFoundExceptionHandler;
import com.codetech.oauth2.exceptions.ParameterErrorException;
import com.codetech.oauth2.exceptions.UsernameAlreadyExistsException;
import com.codetech.oauth2.model.BusinessCategory;
import com.codetech.oauth2.model.BusinessRegistionType;
import com.codetech.oauth2.model.UserProfile;
import com.codetech.oauth2.repository.BusinessCategoryRepository;
import com.codetech.oauth2.repository.BusinessRegistionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/23/2019
 * Time: 12:27 PM
 */
@Service
public class BusinessCategoryServiceImpl {
    @Autowired
    BusinessCategoryRepository businessCategoryRepository;

    @Autowired
    BusinessRegistionTypeRepository businessRegistionTypeRepository;

    @Autowired
    private TransformServiceImpl transformService;

    public BusinessCategory addBusinessCategory(BusinessCategoryDTO businessCategoryDTO) throws ClassNotFoundException {
        BusinessCategory businessCategory = (BusinessCategory) transformService.convertToEntity(businessCategoryDTO, Constant.BUSINESS_CATEGORY_ENTITY_CLASS);

        BusinessCategory businessCategory1 = businessCategory;
        try {
            return businessCategoryRepository.save(businessCategory1);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("business category already exists");
        }

    }

    public List<BusinessCategory> listBusinessCategory(){
        List<BusinessCategory> getAllBusinessCategory = businessCategoryRepository.findAll();
        return getAllBusinessCategory;
    }

    public BusinessRegistionTypeDTO addBusinessRegistionType(@Valid BusinessRegistionTypeDTO businessRegistionTypeDTO) {
        try {
            BusinessRegistionType businessRegistionType = (BusinessRegistionType) transformService.convertToEntity(businessRegistionTypeDTO, Constant.BUSINESS_REGISTION_TYPE_ENTITY_CLASS);


            return (BusinessRegistionTypeDTO) transformService.convertToDto(businessRegistionTypeRepository.save(businessRegistionType), Constant.BUSINESS_REGISTION_TYPE_DTO_CLASS);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Business Registation type already exists");
        }
    }

    public BusinessCategory updateBusinessCategory(BusinessCategory businessCategory, String id) {
        if(businessCategoryRepository.findById(Long.valueOf(id)).isPresent()){
        BusinessCategory businessCategory1 = businessCategoryRepository.findById(Long.valueOf(id)).get();
            try {
                businessCategory1.setName(businessCategory.getName());
                return businessCategoryRepository.saveAndFlush(businessCategory1);
            }catch (Exception e){
                throw new UsernameAlreadyExistsException("Business Category type already exists");
            }
        }
        throw new BusinessCategoryNotFoundExceptionHandler();
    }

    public BusinessRegistionTypeDTO updateBusinessRegistionType(BusinessRegistionTypeDTO businessRegistionTypeDTO, String id) {
        if(businessRegistionTypeRepository.findById(Long.valueOf(id)).isPresent()){
            try {
                BusinessRegistionType businessRegistionType1 = businessRegistionTypeRepository.findById(Long.valueOf(id)).get();
                businessRegistionType1.setName(businessRegistionTypeDTO.getName());
//                return businessRegistionTypeRepository.saveAndFlush(businessRegistionType1);
                return (BusinessRegistionTypeDTO) transformService.convertToDto(businessRegistionTypeRepository.saveAndFlush(businessRegistionType1), Constant.BUSINESS_REGISTION_TYPE_DTO_CLASS);

            }catch (Exception e){
                throw new UsernameAlreadyExistsException("Business Registation type already exists");
            }
        }
        throw new BusinessRegistationTypeNotFoundExceptionHandler();
    }

    public BusinessCategory BusinessCategoryById(String id) {
        if(isNumeric(id)== true) {
            if(businessCategoryRepository.findById(Long.valueOf(id)).isPresent()) {
                BusinessCategory businessCategory = businessCategoryRepository.findById(Long.valueOf(id)).get();
                return businessCategory;
            }else{
                throw new BusinessCategoryNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }

    }
}
