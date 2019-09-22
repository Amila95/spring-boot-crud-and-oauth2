package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BusinessProductDTO;
import com.codetech.oauth2.dto.BusinessServiceDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.BusinessProduct;
import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.BusinessService;
import com.codetech.oauth2.model.User;
import com.codetech.oauth2.repository.BusinessProductRepository;
import com.codetech.oauth2.repository.BusinessProfileRepository;
import com.codetech.oauth2.repository.BusinessServiceRepository;
import com.codetech.oauth2.repository.UserRepository;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/5/2019
 * Time: 5:25 AM
 */
@Service
public class BusinessServiceServiceImpl {

    @Autowired
    private TransformServiceImpl transformService;

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private BusinessServiceRepository businessServiceRepository;

    @Autowired
    private UserRepository userRepository;

    public BusinessServiceDTO addBusinessService(BusinessServiceDTO businessServiceDTO, String id, String name) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            BusinessService businesService = (BusinessService) transformService.convertToEntity(businessServiceDTO, Constant.BUSINESS_SERVICE_ENTITY_CLASS);
            if(userRepository.findByUsername(name) != null) {
                if (businessProfileRepository.findById(Long.valueOf(id)).isPresent()) {
                    BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
                    businesService.setBusinessProfile(businessProfile);
                    User user = userRepository.findByUsername(name);
                    businesService.setUser(user);
//        businessProductRepository.save(businessProduct);
                    return  (BusinessServiceDTO)transformService.convertToDto(businessServiceRepository.save(businesService), Constant.BUSINESS_SERVICE_DTO_CLASS);
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

    public BusinessServiceDTO updateBusinessService(BusinessServiceDTO businessServiceDTO, String id, String name) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            if (userRepository.findByUsername(name) != null) {
                if (businessServiceRepository.findById(Long.valueOf(id)).isPresent()) {
                    BusinessService businessService = businessServiceRepository.findById(Long.valueOf(id)).get();
                    User user = userRepository.findByUsername(name);
                    if(businessService.getUser().equals(user)) {
                        businessService.setTitle(businessServiceDTO.getTitle());
                        businessService.setDescription(businessServiceDTO.getDescription());
                        return (BusinessServiceDTO) transformService.convertToDto(businessServiceRepository.saveAndFlush(businessService), Constant.BUSINESS_SERVICE_DTO_CLASS);
                    }else{
                        throw new BusinessServicePremitionHandler();
                    }
                } else {
                    throw new BusinessServiceNotFoundExceptionHandler();
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

    public List<BusinessServiceDTO> getAllBusinessService() throws ClassNotFoundException {
        return (List<BusinessServiceDTO>) transformService.convertToDtoList(businessServiceRepository.findAll(), Constant.BUSINESS_PRODUCT_DTO_CLASS);


    }

    public BusinessServiceDTO getBusinessServiceByBusinessServiceId(String id) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            if(businessServiceRepository.findById(Long.valueOf(id)).isPresent()){
                BusinessService businessService = businessServiceRepository.findById(Long.valueOf(id)).get();
                return (BusinessServiceDTO) transformService.convertToDto(businessService, Constant.BUSINESS_SERVICE_DTO_CLASS);
            }else{
                throw new BusinessServiceNotFoundExceptionHandler();
            }

        }else {
            throw new ParameterErrorException();
        }
    }

    public List<BusinessProductDTO> getBusinessServiceByBusinessProfileId(String id) throws ClassNotFoundException {
        if(isNumeric(id)== true) {
            if(businessServiceRepository.findById(Long.valueOf(id)).isPresent()) {
                BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
                return (List<BusinessProductDTO>) transformService.convertToDtoList(businessServiceRepository.findByBusinessProfile(businessProfile), Constant.BUSINESS_PRODUCT_DTO_CLASS);
            }else{
                throw new BusinessProfileNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

}
