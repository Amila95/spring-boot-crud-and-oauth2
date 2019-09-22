package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BusinessProfileDTO;
import com.codetech.oauth2.dto.BusinessProfileReturnDTO;
import com.codetech.oauth2.dto.UserBusinessProfileDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.*;
import com.codetech.oauth2.repository.*;
import com.codetech.oauth2.service_interface.BusinessProfileServiceInterface;
import com.codetech.oauth2.validator.IsNullable;
import com.codetech.oauth2.validator.IsNumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.codetech.oauth2.validator.IsNullable.isNullable;
import static com.codetech.oauth2.validator.IsNumeric.isNumeric;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:06 PM
 */
@Service
public class BusinessProfileServiceImpl implements BusinessProfileServiceInterface {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransformServiceImpl transformService;

    @Autowired
    private UserBusinessProfileRepository userBusinessProfileRepository;

    @Autowired
    private BusinessCategoryRepository businessCategoryRepository;

    @Autowired
    private BusinessRegistionTypeRepository businessRegistionTypeRepository;

    @Autowired
    private IsNumeric isNumeric;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private IsNullable isNullable;


    public BusinessProfile getProfileById(Long id) throws IllegalArgumentException, ClassNotFoundException {
        if (!businessProfileRepository.existsById(id)) throw new BusinessProfileNotFoundExceptionHandler();
        Optional<BusinessProfile> businessProfile = businessProfileRepository.findById(id);
//        BusinessProfileDTO businessProfileDTO = (BusinessProfileDTO) transformService.convertToDto(businessProfile, Constant.BUSINESS_PROFILE_DTO_CLASS);
        return businessProfile.get();

    }

    public BusinessProfileReturnDTO addProfile(@Valid @RequestBody BusinessProfileDTO businessProfileDTO, String userName) throws ClassNotFoundException {
        BusinessProfile businessProfile = (BusinessProfile) transformService.convertToEntity(businessProfileDTO, Constant.BUSINESS_PROFILE_ENTITY_CLASS);
        if (businessProfileRepository.findProfileById(businessProfile.getId())==null) {
            if(businessProfileRepository.existsByEmailId(businessProfile.getEmailId())) throw new EmailIdAlreadyExistExceptionHandler();
            if(businessProfileDTO.getRole() == null || businessProfileDTO.getRole() == "") throw new RoleEmptyExceptionHandler();
            businessProfileDTO.getBusinessCategorySet().forEach(category -> {if( category.getId() == null  ) {
                throw new BusinessCategoryNotFoundExceptionHandler();}}
            );
            businessProfileDTO.getBusinessCategorySet().forEach(category -> {if(!businessCategoryRepository.findById(category.getId()).isPresent()  ) {
                throw new BusinessCategoryNotFoundExceptionHandler();}}
            );
            if(businessProfileDTO.getBusinessRegistionTypes() == null  || businessProfileDTO.getBusinessRegistionTypes() ==null  ) {
                throw new BusinessRegistationTypeNotFoundExceptionHandler();
            }
            if(isNumeric(businessProfileDTO.getBusinessRegistionTypes())== false) {
                throw new BusinessRegistationTypeNotFoundExceptionHandler();
            }
            if(! businessRegistionTypeRepository.findById(Long.valueOf(businessProfileDTO.getBusinessRegistionTypes())).isPresent()){
                throw new BusinessRegistationTypeNotFoundExceptionHandler();
            }
            if(isNullable(businessProfileDTO.getBusinessRegistrationNumber()) || isNullable(businessProfileDTO.getName() )){
                throw new NullableValuesIsFound();
            }
//            businessProfile.setBusinessCategorySet(businessProfileDTO.getBusinessCategorySet());
            BusinessRegistionType businessRegistionType= businessRegistionTypeRepository.findById(Long.valueOf(businessProfileDTO.getBusinessRegistionTypes())).get();
             businessProfile.setBusinessRegistionTypes(businessRegistionType);
                BusinessProfile saveBusinessProfile = businessProfileRepository.save(businessProfile);
                User user = userRepository.findByUsername(userName);
                UserBusinessProfileDTO userBusinessProfileDTO = new UserBusinessProfileDTO();
                userBusinessProfileDTO.setUser(user);
                userBusinessProfileDTO.setBusinessProfile(saveBusinessProfile);
                userBusinessProfileDTO.setRole(businessProfileDTO.getRole());

                UserBusinessProfile userBusinessProfile = (UserBusinessProfile) transformService.convertToEntity(userBusinessProfileDTO, Constant.USER_BUSINESS_PROFILE_ENTITY_CLASS);
                userBusinessProfileRepository.save(userBusinessProfile);
                return (BusinessProfileReturnDTO) transformService.convertToDto(saveBusinessProfile, Constant.BUSINESS_PROFILE_RETURN_DTO_CLASS);
        } else {
            throw new ProfileAlreadyExistExceptionHandler();
        }
    }

    public List<BusinessProfile> getAllProfiles() throws ClassNotFoundException {
        List<BusinessProfile> all = businessProfileRepository.findAll();
        return all;
    }


    public BusinessProfileDTO updateProfile(BusinessProfileDTO businessProfileDTO, Long id, String name) throws ClassNotFoundException, ConstraintViolationException {
        BusinessProfile businessProfile = (BusinessProfile) transformService.convertToEntity(businessProfileDTO, Constant.BUSINESS_PROFILE_ENTITY_CLASS);
        if (!businessProfileRepository.existsById(id)) throw new ProfileExceptionHandler();
        BusinessProfile oldBusinessProfile = businessProfileRepository.findProfileById(id);
        User user = userRepository.findByUsername(name);
        UserBusinessProfile userBusinessProfile = userBusinessProfileRepository.findByBusinessProfile(oldBusinessProfile);
        businessProfileDTO.getBusinessCategorySet().forEach(category -> {if(!businessCategoryRepository.findById(category.getId()).isPresent()){
            throw new BusinessCategoryNotFoundExceptionHandler();}}
        );

        if(businessProfileDTO.getBusinessRegistionTypes() == null  || businessProfileDTO.getBusinessRegistionTypes() ==null  ) {
            throw new ParameterErrorException();
        }
        if(isNumeric(businessProfileDTO.getBusinessRegistionTypes().toString())== false) {
            throw new ParameterErrorException();
        }
        if(! businessRegistionTypeRepository.findById(Long.valueOf(businessProfileDTO.getBusinessRegistionTypes())).isPresent()){
            throw new BusinessRegistationTypeNotFoundExceptionHandler();
        }
        if(isNullable(businessProfileDTO.getBusinessRegistrationNumber()) || isNullable(businessProfileDTO.getName() )){
            throw new NullableValuesIsFound();
        }
        if(userBusinessProfile.getUser().equals(user)){
            if(oldBusinessProfile.getEmailId().equals(businessProfile.getEmailId()) || !oldBusinessProfile.getEmailId().equals(businessProfile.getEmailId()) && !businessProfileRepository.existsByEmailId(businessProfile.getEmailId())) {
                oldBusinessProfile.setEmailId(businessProfile.getEmailId());
                oldBusinessProfile.setName(businessProfile.getName());
                oldBusinessProfile.setBusinessRegistrationNumber(businessProfile.getBusinessRegistrationNumber());
                oldBusinessProfile.setContactNumber01(businessProfile.getContactNumber01());
                oldBusinessProfile.setContactNumber02(businessProfile.getContactNumber02());
                oldBusinessProfile.setLogo(businessProfile.getLogo());
//                oldBusinessProfile.setBusinessCategorySet(businessProfile.getBusinessCategorySet());
                oldBusinessProfile.setBusinessRegistionTypes(businessProfile.getBusinessRegistionTypes());
                BusinessProfile newBusinessProfile = businessProfileRepository.saveAndFlush(oldBusinessProfile);
                return (BusinessProfileDTO) transformService.convertToDto(newBusinessProfile, Constant.BUSINESS_PROFILE_DTO_CLASS);
            }else{
                throw new EmailIdAlreadyExistExceptionHandler();
            }
        } else {
            throw new UserNotFoundExceptionHandler();
        }
    }

    public BusinessProfileDTO updateProfileStatus(Boolean status, Long id) throws ClassNotFoundException, ConstraintViolationException {
        if (!businessProfileRepository.existsById(id)) throw new ProfileExceptionHandler();
        BusinessProfile oldBusinessProfile = businessProfileRepository.findProfileById(id);
        oldBusinessProfile.setStatus(status);

        BusinessProfile newBusinessProfile = businessProfileRepository.saveAndFlush(oldBusinessProfile);
        return (BusinessProfileDTO) transformService.convertToDto(newBusinessProfile, Constant.BUSINESS_PROFILE_DTO_CLASS);
    }

    public BusinessProfileDTO updateProfileDelete(Boolean delete, Long id) throws ClassNotFoundException, ConstraintViolationException {
        if (!businessProfileRepository.existsById(id)) throw new ProfileExceptionHandler();
        BusinessProfile oldBusinessProfile = businessProfileRepository.findProfileById(id);
        oldBusinessProfile.setDeleted(delete);


        BusinessProfile newBusinessProfile = businessProfileRepository.saveAndFlush(oldBusinessProfile);
        return (BusinessProfileDTO) transformService.convertToDto(newBusinessProfile, Constant.BUSINESS_PROFILE_DTO_CLASS);
    }


    public List<?> getProfileByUser(String name) throws ClassNotFoundException {
        User user = userRepository.findByUsername(name);
        if(user != null) {
            return getProfileByUserId(String.valueOf(user.getId()));

        }else{
            throw new ProfileExceptionHandler();
        }

    }

    public List<?> getProfileByUserId(String id) {
        if(isNumeric(id)== true) {
            Optional<User> user = userRepository.findById(Long.valueOf(id));
            if (user.isPresent()) {
                List<UserBusinessProfile> userBusinessProfiles = userBusinessProfileRepository.findByUserId(user.get().getId());
                return userBusinessProfiles;
            } else {
                throw new UserNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }

    }

    public BusinessProfile updateBusinessProfileByState(String id) {
        if(isNumeric(id)== true) {
            Optional<BusinessProfile> businessProfile = businessProfileRepository.findById(Long.valueOf(id));
            if (businessProfile.isPresent()) {
                businessProfile.get().setStatus(!businessProfile.get().isStatus());
                return businessProfileRepository.save(businessProfile.get());

            } else {
                throw new BusinessProfileNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

    public List<BusinessRegistionType> listBusinessRegistionType() {
        List<BusinessRegistionType> getAllBusinessCategory = businessRegistionTypeRepository.findAll();
        return getAllBusinessCategory;

    }


    public Address addAddress(Address address, String id) {
        if(isNumeric(id)== true) {
            Optional<BusinessProfile> businessProfile1 = businessProfileRepository.findById(Long.valueOf(id));
            if(businessProfile1.isPresent()) {
                BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
                //Address address2 = addressRepository.findByBusinssProfile(businessProfile);
                if (addressRepository.existsByBusinessProfile(businessProfile)) {
                    throw new BusinessAddressAlreayExceptionHandler();
                }

                Address address1 = new Address();
                address1 = address;
                address1.setBusinessProfile(businessProfile);
                return addressRepository.save(address1);
            }else{
                throw new BusinessProfileNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

    public Address updateAddress(Address address, String id) {
        if(isNumeric(id)== true) {
            Optional<BusinessProfile> businessProfile1 = businessProfileRepository.findById(Long.valueOf(id));
            if(businessProfile1.isPresent()) {
                BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
                //Address address2 = addressRepository.findByBusinssProfile(businessProfile);
                if (!addressRepository.existsByBusinessProfile(businessProfile)) {
                    throw new BusinessProfileNotFoundExceptionHandler();
                }

                Address address2 = addressRepository.findByBusinessProfile(businessProfile);

                address2.setCity(address.getCity());
                address2.setCountry(address.getCountry());
                address2.setLine1(address.getLine1());
                address2.setLine2(address.getLine2());
                address2.setLine3(address.getLine3());
                address2.setZipCode(address.getZipCode());
//                address1.setBusinessProfile(businessProfile);
                return addressRepository.saveAndFlush(address2);
            }else{
                throw new BusinessProfileNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }
    }

    public BusinessRegistionType getBusinessRegistionType(String id) {
        if(isNumeric(id)== true) {
            if(businessRegistionTypeRepository.findById(Long.valueOf(id)).isPresent()) {
                BusinessRegistionType businessRegistionType = businessRegistionTypeRepository.findById(Long.valueOf(id)).get();
                return businessRegistionType;
            }else{
                throw new BusinessRegistationTypeNotFoundExceptionHandler();
            }
        }else{
            throw new ParameterErrorException();
        }

    }
}

