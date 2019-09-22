package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.Branch;
import com.codetech.oauth2.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Supun Dilshan.
 * Date: 09/04/2019
 * Time: 10:02 PM
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {


    List<Branch> findByBusinessProfiles(BusinessProfile businessProfile);
}

