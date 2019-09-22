package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BusinessCategoryDTO;
import com.codetech.oauth2.dto.BusinessProductDTO;
import com.codetech.oauth2.dto.BusinessProfileDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.BusinessProduct;
import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.BusinessProductRepository;
import com.codetech.oauth2.repository.BusinessProfileRepository;
import com.codetech.oauth2.repository.UserRepository;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/4/2019
 * Time: 9:33 AM
 */

@Service
public class BusinessProductServiceImpl {

    @Autowired
    private TransformServiceImpl transformService;

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private BusinessProductRepository businessProductRepository;

    @Autowired
    private UserRepository userRepository;

    public BusinessProductDTO addBusinessCategory(BusinessProductDTO businessProductDTO, String id, String name) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            BusinessProduct businessProduct = (BusinessProduct) transformService.convertToEntity(businessProductDTO, Constant.BUSINESS_PRODUCT_ENTITY_CLASS);
            if(userRepository.findByUsername(name) != null) {
                if (businessProfileRepository.findById(Long.valueOf(id)).isPresent()) {
                    BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
                    businessProduct.setBusinessProfile(businessProfile);
                    User user = userRepository.findByUsername(name);
                    businessProduct.setUser(user);
//        businessProductRepository.save(businessProduct);
                    return (BusinessProductDTO) transformService.convertToDto(businessProductRepository.save(businessProduct), Constant.BUSINESS_PRODUCT_DTO_CLASS);
                } else {
                    throw new BusinessProfileNotFoundExceptionHandler();
                }
            }else{
                throw new UserNotFoundExceptionHandler();
            }
        }
        else{
            throw new ParameterErrorException();
        }

    }

    public BusinessProductDTO updateBusinessCategory(BusinessProductDTO businessProductDTO, String id, String name) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            if (userRepository.findByUsername(name) != null) {
                if (businessProductRepository.findById(Long.valueOf(id)).isPresent()) {
                    BusinessProduct businessProduct = businessProductRepository.findById(Long.valueOf(id)).get();
                    User user = userRepository.findByUsername(name);
                    if(businessProduct.getUser().equals(user)) {
                        businessProduct.setTitle(businessProductDTO.getTitle());
                        businessProduct.setDescription(businessProductDTO.getDescription());
                        return (BusinessProductDTO) transformService.convertToDto(businessProductRepository.saveAndFlush(businessProduct), Constant.BUSINESS_PRODUCT_DTO_CLASS);
                    }else{
                        throw new BusinessProductPremitionExcepationHandler();
                    }
                } else {
                    throw new BusinessProductNotFoundExceptionHandler();
                }

            }
            else {
                throw new UserNotFoundExceptionHandler();
            }
        }
        else{
            throw new ParameterErrorException();
        }

    }

    public BusinessProductDTO getBusinessProductByBusinessProductId(String id) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            if(businessProductRepository.findById(Long.valueOf(id)).isPresent()){
                BusinessProduct businessProduct = businessProductRepository.findById(Long.valueOf(id)).get();
                return (BusinessProductDTO) transformService.convertToDto(businessProduct, Constant.BUSINESS_PRODUCT_DTO_CLASS);
            }else{
                throw new BusinessProductNotFoundExceptionHandler();
            }

        }else {
            throw new ParameterErrorException();
        }
    }


    public List<BusinessProductDTO> getBusinessProductByBusinessProfileId(String id) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
            return (List<BusinessProductDTO>) transformService.convertToDtoList(businessProductRepository.findByBusinessProfile(businessProfile), Constant.BUSINESS_PRODUCT_DTO_CLASS);
        }else{
            throw new ParameterErrorException();
        }
    }

    public  List<BusinessProductDTO> getAllBusinessProduct() throws ClassNotFoundException {

        return (List<BusinessProductDTO>) transformService.convertToDtoList(businessProductRepository.findAll(), Constant.BUSINESS_PRODUCT_DTO_CLASS);
    }
}
