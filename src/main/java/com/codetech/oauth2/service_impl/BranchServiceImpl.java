package com.codetech.oauth2.service_impl;

import com.codetech.oauth2.constant.Constant;
import com.codetech.oauth2.dto.BranchDTO;
import com.codetech.oauth2.exceptions.*;
import com.codetech.oauth2.model.Branch;
import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.repository.BranchRepository;
import com.codetech.oauth2.repository.BusinessProfileRepository;
import com.codetech.oauth2.validator.IsNullable;
import com.codetech.oauth2.validator.IsNumeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codetech.oauth2.validator.IsNullable.isNullable;
import static com.codetech.oauth2.validator.IsNumeric.isNumeric;


/**
 * Created by Supun Dilshan.
 * Date: 09/04/2019
 * Time: 10:29 PM
 */


@Service
public class BranchServiceImpl {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    BusinessProfileRepository businessProfileRepository;

    @Autowired
    IsNullable isNullable;

    @Autowired
    IsNumeric isNumeric;

    @Autowired
    private TransformServiceImpl transformService;

    //add a new branch
    public BranchDTO addBranch(BranchDTO branchDTO, String id) throws ClassNotFoundException {

        if (businessProfileRepository.findById(Long.valueOf(id)).isPresent()) {

            BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
            Branch branch = (Branch) transformService.convertToEntity(branchDTO, Constant.BRANCH_ENTITY_CLASS);

            branch.setBusinessProfiles(businessProfile);
            if (isNullable(branch.getLocation())) {
                throw new NullableValuesIsFound();
            }

            return (BranchDTO) transformService.convertToDto(branchRepository.save(branch), Constant.BRANCH_DTO_CLASS);

        } else {
            throw new BusinessProfileNotFoundExceptionHandler();
        }

    }


    //update branch details
    public BranchDTO updateBranch(BranchDTO branchDTO, String branchId) throws ClassNotFoundException {

        if (isNumeric(branchId) == true) {
            if (branchRepository.findById(Long.valueOf(branchId)).isPresent()) {

                Branch branch = branchRepository.findById(Long.valueOf(branchId)).get();
                branch.setContactNo(branchDTO.getContactNo());
                branch.setLocation(branchDTO.getLocation());

                if (isNullable(branch.getLocation())) {
                    throw new NullableValuesIsFound();
                }
                return ((BranchDTO) transformService.convertToDto(branchRepository.saveAndFlush(branch), Constant.BRANCH_DTO_CLASS));

            } else {
                throw new BranchNotFoundExceptionHandler();
            }
        } else {
            throw new ParameterErrorException();
        }
    }


    //delete the branch
    public void deleteBranch(String branchId) throws ClassNotFoundException {

        if (!isNumeric(branchId.trim())) {
            throw new NumericalValueNotFoundExceptionHandler();
        }
        if (!branchRepository.findById(Long.valueOf(branchId)).isPresent()) {
            throw new BranchNotFoundExceptionHandler();
        }
        Branch branch = branchRepository.findById(Long.valueOf(branchId)).get();
        if (branch.isState()) {
            branch.setState(false);
            branchRepository.saveAndFlush(branch);
        } else {
            throw new BranchAlreadyDeletedExceptionHandler();
        }
    }


    //retrieve all the branches
    public List<BranchDTO> listAllBranches() throws ClassNotFoundException {

        return (List<BranchDTO>) transformService.convertToDtoList(branchRepository.findAll(), Constant.BRANCH_DTO_CLASS);
    }


    //retrieve a branch by its id
    public BranchDTO retrieveBranchById(String id) throws ClassNotFoundException {
        return (BranchDTO) transformService.convertToDto(branchRepository.findById(Long.valueOf(id)).get(), Constant.BRANCH_DTO_CLASS);
    }


    //retrieve all the branches of a particular company
    public List<BranchDTO> retrieveAllBranchByBusinessProfile(String id) throws ClassNotFoundException {

        if (!businessProfileRepository.findById(Long.valueOf(id)).isPresent()) {
            throw new BusinessProfileNotFoundExceptionHandler();
        }

        BusinessProfile businessProfile = businessProfileRepository.findById(Long.valueOf(id)).get();
        List<Branch> branches = branchRepository.findByBusinessProfiles(businessProfile);
        return (List<BranchDTO>) transformService.convertToDtoList(branches, Constant.BRANCH_DTO_CLASS);
    }
}




