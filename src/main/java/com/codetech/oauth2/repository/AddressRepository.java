package com.codetech.oauth2.repository;

import com.codetech.oauth2.model.Address;
import com.codetech.oauth2.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/27/2019
 * Time: 6:22 PM
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    boolean existsByBusinessProfile(BusinessProfile businessProfile);

    Address findByBusinessProfile(BusinessProfile businessProfile);
}
