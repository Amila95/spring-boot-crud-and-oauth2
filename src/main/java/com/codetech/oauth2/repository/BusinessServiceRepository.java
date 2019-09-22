package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.BusinessProfile;
import com.codetech.oauth2.model.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/5/2019
 * Time: 5:22 AM
 */
public interface BusinessServiceRepository extends JpaRepository<BusinessService, Long> {
    List<BusinessService> findByBusinessProfile(BusinessProfile businessProfile);
}
