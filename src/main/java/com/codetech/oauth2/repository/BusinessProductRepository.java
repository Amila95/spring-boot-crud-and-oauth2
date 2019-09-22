package com.codetech.oauth2.repository;


import com.codetech.oauth2.model.BusinessProduct;
import com.codetech.oauth2.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Amila Wickramarathne.
 * Date: 9/4/2019
 * Time: 9:32 AM
 */
public interface BusinessProductRepository extends JpaRepository<BusinessProduct, Long> {
    List<BusinessProduct> findByBusinessProfile(BusinessProfile businessProfile);
}
